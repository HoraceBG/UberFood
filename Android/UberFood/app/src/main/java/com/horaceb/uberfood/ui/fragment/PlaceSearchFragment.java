package com.horaceb.uberfood.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.horaceb.uberfood.R;
import com.horaceb.uberfood.data.model.Places;
import com.horaceb.uberfood.data.model.Results;
import com.horaceb.uberfood.data.request.PlacesService;
import com.horaceb.uberfood.ui.activity.PlaceActivity;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.horaceb.uberfood.data.Constants.PLACES;

/**
 * Here's where the user can initiate a search for restaurants
 * <p/>
 * Created by HoraceBG on 22/06/2015.
 */
public class PlaceSearchFragment extends Fragment implements Callback<Places>, LocationListener, ShakeDetector.Listener {

    @InjectView(R.id.root_view)
    View rootView;

    @InjectView(R.id.progress_container)
    View progressContainer;

    @InjectView(R.id.content_container)
    View contentContainer;

    private LocationManager locationManager;

    private Location location;

    private SensorManager sensorManager;

    private ShakeDetector shakeDetector;

    public static PlaceSearchFragment newInstance() {
        return new PlaceSearchFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        shakeDetector.start(sensorManager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.restaurant_search_button)
    public void onSearchButtonPressed() {
        sendRequest();
    }

    private String getLocation() {
        String latLng = "%s, %s";
        if (location == null) {
            // We haven't received a location update yet
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return String.format(latLng, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
        }
        return String.format(latLng, location.getLatitude(), location.getLongitude());
    }

    @Override
    public void success(Places places, Response response) {
        final List<Results> restaurants = places.getResults();

        if (restaurants.isEmpty()) {
            Snackbar.make(rootView, R.string.no_restaurants_found, Snackbar.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getActivity(), PlaceActivity.class);
            intent.putParcelableArrayListExtra(PLACES, new ArrayList<>(restaurants));
            getActivity().startActivity(intent);
            displayContent();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
        displayContent();
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
        shakeDetector.stop();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void hearShake() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            // Let the user know they've successfully shaken their device
            vibrator.vibrate(500);
            sendRequest();
        }
    }

    private void sendRequest() {
        PlacesService service = new PlacesService();
        service.getWebService().getRestaurants(getLocation(), 500, this);
        displayProgress();
    }

    private void displayProgress() {
        progressContainer.setVisibility(View.VISIBLE);
        contentContainer.setVisibility(View.GONE);
    }

    private void displayContent() {
        progressContainer.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
    }
}
