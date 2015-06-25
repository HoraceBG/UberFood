package com.horaceb.uberfood.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.horaceb.uberfood.data.model.Results;
import com.horaceb.uberfood.ui.fragment.PlaceFragment;

import java.util.List;

public class PlacePagerAdapter extends FragmentPagerAdapter {

    private List<Results> restaurants;

    public PlacePagerAdapter(FragmentManager fm, final List<Results> restaurants) {
        super(fm);
        this.restaurants = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceFragment.newInstance(restaurants.get(position));
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return restaurants.get(position).getName();
    }

}
