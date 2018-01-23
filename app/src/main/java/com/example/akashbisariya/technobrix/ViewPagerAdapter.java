package com.example.akashbisariya.technobrix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by akash bisariya on 21-01-2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ModelData.Data> eventArrayList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm, ArrayList<ModelData.Data> eventList) {
        super(fm);
        eventArrayList.addAll(eventList);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new MapFragment();
            case 1:
                ListFragment listFragment =new ListFragment();
                Bundle bundle = new Bundle();
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
