package com.example.akashbisariya.technobrix;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by akash bisariya on 21-01-2018.
 */

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    ArrayList<ModelData.Data> arrayList = new ArrayList<>();
    private ArrayList<LatLng> latLongList = new ArrayList<>();
    private ArrayList<String> urlList = new ArrayList<>();
    private ArrayList<Bitmap> bitmapList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        arrayList.addAll((ArrayList<ModelData.Data>)getArguments().getSerializable("eventList"));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for(int i=0;i<arrayList.size();i++)
        {
            LatLng latLng = new LatLng(Double.parseDouble(arrayList.get(i).getLatitude()),Double.parseDouble(arrayList.get(i).getLongitude()));
            latLongList.add(latLng);
            urlList.add(arrayList.get(i).getCategoryImage());

        }
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapFragment.this);
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for(int j=0;j<urlList.size();j++)
        {
            setIcon(urlList.get(j));
            if(j==urlList.size()-1)
            {
                for(int i=0;i<arrayList.size();i++) {
                    MarkerOptions markerOptions = new MarkerOptions().position(latLongList.get(i));
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmapList.get(i)));
                    googleMap.addMarker(markerOptions);
                    builder.include(latLongList.get(i));
                }
                LatLngBounds latLngBounds = builder.build();
                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, padding);
                googleMap.animateCamera(cu);
            }
        }

    }

    private void setIcon(String url) {

        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                       bitmapList.add(resource);
                    }
                });
    }
}
