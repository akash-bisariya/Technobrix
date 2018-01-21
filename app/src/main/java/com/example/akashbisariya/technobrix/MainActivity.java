package com.example.akashbisariya.technobrix;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ViewPager vpMapList;
    TabLayout tbMapList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpMapList=findViewById(R.id.vp_map_list);
        tbMapList=findViewById(R.id.tb_map_list);
        vpMapList.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tbMapList.setupWithViewPager(vpMapList);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://evenzt.com/beta/eventmanagement/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        IApiService apiService = retrofit.create(IApiService.class);
        HashMap<String,Double> hashMap = new HashMap<>();

        hashMap.put("latitude",28.56202190822998);
        hashMap.put("longitude",77.2709018737077);
        hashMap.put("userId",5.0);
        Call<ModelData> call = apiService.getData(hashMap);
        call.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {
                Log.d("","");
            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {
                Log.d("","");
            }
        });


    }
}
