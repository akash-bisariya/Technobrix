package com.example.akashbisariya.technobrix;

import com.google.android.gms.maps.GoogleMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by akash bisariya on 22-01-2018.
 */

public interface IImageCallback {
    void imageDownloadComplete(GoogleMap googleMap);
}
