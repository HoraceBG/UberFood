package com.horaceb.uberfood.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Handles the location manager
 * Created by HoraceBG on 25/06/2015.
 */
public abstract class LocationAwareFragment extends Fragment implements LocationListener {

    private LocationManager locationManager;

    private Location location;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
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


}
