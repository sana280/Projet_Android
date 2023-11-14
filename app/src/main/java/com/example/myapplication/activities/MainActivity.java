package com.example.myapplication.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AdAdapter;
import com.example.myapplication.database.AdsDatabase;
import com.example.myapplication.entities.Ad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_AD = 1;
    private RecyclerView AdRecyclerView;
    private List<Ad> adList;
    private AdAdapter adAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAddAdMain = findViewById(R.id.imageAddAdMain);
        imageAddAdMain.setOnClickListener((v)-> {
                startActivityForResult( new Intent(getApplicationContext(), CreateAdActivity.class),
                        REQUEST_CODE_ADD_AD
                );

        });

        AdRecyclerView = findViewById(R.id.AdRecyclerView);
        AdRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );

        adList = new ArrayList<>();
        adAdapter = new AdAdapter(adList);
        AdRecyclerView.setAdapter(adAdapter);
        getAds();
    }

    private void getAds() {
        @SuppressLint("StaticFieldLeak")
        class GetAdsTask extends AsyncTask<Void, Void, List<Ad>>{

            @Override
            protected List<Ad> doInBackground(Void... voids) {
                return AdsDatabase
                        .getDatabase(getApplicationContext())
                        .adDao().getAllAds();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<Ad> ads) {
                super.onPostExecute(ads);
                if(adList.size()==0 ) {
                  adList.addAll(ads);
                  adAdapter.notifyDataSetChanged();
                } else {
                    adList.add(0,ads.get(0));
                    adAdapter.notifyItemInserted(0);
                }
                AdRecyclerView.smoothScrollToPosition(0);
            }
        }

        new GetAdsTask().execute();
    }

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
     super.onActivityResult(requestCode, resultCode, data);
     if(requestCode == REQUEST_CODE_ADD_AD && resultCode == RESULT_OK) {
         getAds();
      }
}
}