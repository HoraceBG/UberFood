package com.horaceb.uberfood.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.horaceb.uberfood.R;
import com.horaceb.uberfood.data.model.Location;
import com.horaceb.uberfood.data.model.Results;
import com.horaceb.uberfood.util.UrlCreator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Displays a selected place to the user
 */
public class PlaceFragment extends Fragment {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @InjectView(R.id.place_image)
    ImageView imageView;

    @InjectView(R.id.place_vicinity_text)
    TextView vicinity;

    @InjectView(R.id.open_status_text)
    TextView openStatus;

    @InjectView(R.id.place_rating)
    TextView ratingText;

    @InjectView(R.id.static_map)
    ImageView map;

    private PackageManager packageManager;

    private Results place;


    private static final String PLACE_KEY = "place_key";

    public static PlaceFragment newInstance(final Results restaurant) {
        PlaceFragment fragment = new PlaceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PLACE_KEY, restaurant);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        packageManager = getActivity().getPackageManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        place = getArguments().getParcelable(PLACE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        ButterKnife.inject(this, view);
        loadPlaceImage();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fixToolBarIssue();
        collapsingToolbarLayout.setTitle(place.getName());
        vicinity.setText(place.getVicinity());
        ratingText.setText(getString(R.string.rating, place.getRating()));

        openStatus.setText(getOpenStatusMessage());
        Location location = place.getGeometry().getLocation();
        Glide.with(this).load(UrlCreator.createStaticMapUrl(location.getLat(), location.getLng())).into(map);

        return view;
    }

    private void fixToolBarIssue() {
        // Issue in the Android design library means this needs doing...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int result = 0;
            int resourceId = getResources().getIdentifier(
                    "status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
            CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
            params.topMargin -= result;
            toolbar.setLayoutParams(params);
        }
    }

    private int getOpenStatusMessage() {
        if (place.getOpeningHours() != null) {
            boolean open = Boolean.valueOf(place.getOpeningHours().getOpenNow());
            if (open) {
                return R.string.open;
            }
        }
        return R.string.closed;
    }

    private void loadPlaceImage() {
        Glide.with(this).load(getImageUrl()).into(imageView);
    }

    private String getImageUrl() {
        if (place.getPhotos() == null) {
            // Some random photo
            return "http://lorempixel.com/800/400/food/";
        } else {
            // Just get the first photo
            return UrlCreator.createPhotoUrl(getResources(), place.getPhotos().get(0).getPhotoReference());
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.place_fab)
    public void onFabClicked() {
        final Location location = place.getGeometry().getLocation();
        String message = String.format("Check out this place:\n%s\n\n%s We should go here tonight", place.getName(), UrlCreator.createGoogleMapsUrl(location.getLat(), location.getLng()));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.uber_button)
    public void onUberClicked() {
        Intent intent;
        try {
            packageManager.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            intent = packageManager.getLaunchIntentForPackage("com.ubercab");
            startActivity(intent);


        } catch (PackageManager.NameNotFoundException ex) {
            // Go install on Google play
            String url = "https://play.google.com/store/apps/details?id=com.ubercab";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
