package com.example.pruebaimbox;

import android.os.Bundle;

import com.example.pruebaimbox.ui.main.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.pruebaimbox.databinding.ActivityImagenesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagenesActivity<ViewPagerAdapter> extends AppCompatActivity {

    private ActivityImagenesBinding binding;

    private ViewPager mViewPager;
    private com.example.pruebaimbox.ui.main.ViewPagerAdapter mViewPagerAdapter;
    private String [] imgselec;
    private APIService myApi;
    private String [] fotosRazas;
    private RazasResponse razasResponse;
    private ArrayList<String> urlParam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityImagenesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgselec=new String[3];
        imgselec[0]="https://images.dog.ceo/breeds/hound-afghan/n02088094_5345.jpg";
        imgselec[1]="https://images.dog.ceo/breeds/basenji/n02110806_346.jpg";


            urlParam= new ArrayList<>();
          //  urlParam = savedInstanceState.getStringArrayList("URL_ARRAY");

            //System.out.println(urlParam.get(0).toString());

        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        // Initializing the ViewPagerAdapter

         mViewPagerAdapter=new com.example.pruebaimbox.ui.main.ViewPagerAdapter(ImagenesActivity.this,imgselec);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);



    }



    public void setFotos(String url){


        imgselec= new String[3];
        for (int i=0;i<3;i++){

            imgselec[i]=url;
        }

    }

}