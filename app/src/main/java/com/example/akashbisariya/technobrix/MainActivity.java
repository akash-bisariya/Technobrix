package com.example.akashbisariya.technobrix;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpMapList;
    private TabLayout tbMapList;
    private ViewPagerAdapter vpAdapter;
    private ProgressBar pbProgress;
    private ArrayList<ModelData.Data> eventList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        vpMapList=findViewById(R.id.vp_map_list);
        tbMapList=findViewById(R.id.tb_map_list);
//        vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(),eventList);
//        vpMapList.setAdapter(vpAdapter);
//        tbMapList.setupWithViewPager(vpMapList);
        pbProgress=findViewById(R.id.pb_progress);
        pbProgress.setVisibility(View.VISIBLE);



        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://evenzt.com/eventmanagement/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        IApiService apiService = retrofit.create(IApiService.class);
        HashMap<String,Double> hashMap = new HashMap<>();
        hashMap.put("userId", (double) 5);
        hashMap.put("latitude",28.56202190822998);
        hashMap.put("longitude",77.2709018737077);

        Call<ModelData> call1 =apiService.savePost(5,28.56202190822998,77.2709018737077);
        call1.enqueue(new Callback<ModelData>() {
            @Override
            public void onResponse(Call<ModelData> call, Response<ModelData> response) {
                Log.d("","");
                if(response.body()!=null)
                eventList.addAll(response.body().getData());
//                vpAdapter.notifyDataSetChanged();



                vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(),eventList);
                vpMapList.setAdapter(vpAdapter);
                tbMapList.setupWithViewPager(vpMapList);
                vpMapList.setVisibility(View.VISIBLE);
                pbProgress.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<ModelData> call, Throwable t) {
                Log.d("","");
                pbProgress.setVisibility(View.GONE);
                vpMapList.setVisibility(View.VISIBLE);
            }
        });


    }
}
