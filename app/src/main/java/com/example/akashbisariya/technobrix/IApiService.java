package com.example.akashbisariya.technobrix;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by akash bisariya on 22-01-2018.
 */

public interface IApiService {
@POST("nearbyevent")
Call<ModelData> getData(@Body HashMap userHashmap);
}
