package com.example.akashbisariya.technobrix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by akash bisariya on 21-01-2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ModelData.Data> eventArrayList = new ArrayList<>();
    private ArrayList<LatLng> latLongList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm, ArrayList<ModelData.Data> eventList, ArrayList<LatLng> latLngArrayList) {
        super(fm);
        eventArrayList.addAll(eventList);
        latLongList.addAll(latLngArrayList);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                MapFragment mapFragment = new MapFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("latLongList",latLongList);
                mapFragment.setArguments(bundle);
                return mapFragment;
            case 1:
                ListFragment listFragment =new ListFragment();
                bundle = new Bundle();
                bundle.putSerializable("eventList",eventArrayList);
                listFragment.setArguments(bundle);
                return listFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Map";
            case 1:
                return "List";
            default:
                return "";
        }
    }
}
