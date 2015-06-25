package com.horaceb.uberfood.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.horaceb.uberfood.R;
import com.horaceb.uberfood.data.model.Places;
import com.horaceb.uberfood.data.model.Results;
import com.horaceb.uberfood.ui.adapter.PlacePagerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.horaceb.uberfood.data.Constants.PLACES;

/**
 * Displays a list of Restaurants
 * <p/>
 * Created by HoraceBG on 21/06/2015.
 */
public class PlaceActivity extends AppCompatActivity implements Callback<Places> {

    @InjectView(R.id.restaurant_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.inject(this);

        List<Results> restaurants = getIntent().getParcelableArrayListExtra(PLACES);
        viewPager.setAdapter(new PlacePagerAdapter(getSupportFragmentManager(), restaurants));
    }


    @Override
    public void success(Places places, Response response) {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this, "Error" + error.toString(), Toast.LENGTH_LONG).show();
    }
}
