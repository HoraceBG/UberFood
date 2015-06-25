package com.horaceb.uberfood.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.horaceb.uberfood.R;
import com.horaceb.uberfood.ui.fragment.PlaceSearchFragment;

/**
 * Launches a search for restaurants
 * <p/>
 * Created by HoraceBG on 22/06/2015.
 */
public class RestaurantSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        if (savedInstanceState == null) {
            attachFragment();
        }
    }

    private void attachFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, PlaceSearchFragment.newInstance(), this.getClass().getSimpleName()).commit();
    }

}
