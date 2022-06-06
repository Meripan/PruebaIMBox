package com.example.pruebaimbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebaimbox.databinding.ActivityMainBinding;
import com.example.pruebaimbox.ui.main.ViewPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback <RazasResponse> {


    private ActivityMainBinding binding;
    private RazasResponse datosPerros;
    private APIService myApi;
    private List<String>lista;
    private  String[] arr ;
    private  List<String> urlArray ;
    private List<String>fotosRazas;
    private  String[] razas ;
    private RecyclerView recycler;
    private TextView textViewRV;
    private ImageView imageViewRV;
    private Adapter adaptador;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private String [] imgselec;
    private View itemView;
    private String url;
    private ArrayList<String> array2;
    private View.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        textViewRV=(TextView) findViewById(R.id.textViewBreed);
        recycler = (RecyclerView) findViewById(R.id.dogsRecycler);
        imageViewRV= findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);
        itemView = findViewById(R.id.Cardview);

        urlArray= new ArrayList<>();
        fotosRazas= new ArrayList<>();



        getRetrofit();
        obtenerArrayDatos();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent= new Intent(MainActivity.this, ImagenesActivity.class);
                    startActivity(intent);

            }
        });

    }

    private void obtenerArrayDatos(){

        try {
            //ARRAY RAZAS JSON
            JSONObject jsonObject=new JSONObject(getJsonData());
            JSONObject jsonObject1= jsonObject.getJSONObject("message");



            for(int i=0; i<jsonObject1.length(); i++){

                String mensj = jsonObject1.toString();

                arr = mensj.split(",");
                System.out.println(arr[i]+"AQUI primero"+ arr.length);

                //Array url razas trimmed
                for(int j=0; j< arr.length; j++){

                    String razaToTrim= arr[j];
                    String urlReturn=trimDatosJson(razaToTrim);

                    urlArray.add(urlReturn);
                }

            }
            System.out.println(urlArray.size());


            llamada(urlArray);

            //array para hacer la llamada a imgRandom breed/random 1 valor

            cargarAdapter();

        }
        catch (IOException | JSONException e) {
            e.printStackTrace();


        }


    }


    private String  getJsonData() throws IOException {

        String json =null;
        InputStream inputStream = getAssets().open("razas.json");
        int size= inputStream.available();
        byte [] bufferData= new byte [size];
        inputStream.read(bufferData);
        inputStream.close();
        json= new String(bufferData,"UTF-8");
        return json;

    }


    private void getRetrofit() {
        System.out.println("entramos");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        System.out.println("buildeamos");

        myApi = retrofit.create(APIService.class);
    }

    private void llamada(List<String> url){

      {

        System.out.println(url.size() + "TAMAÑO DEL ARRAY DE URLS");
        for(int i=0; i<url.size(); i++){
            Call<RazasResponse> call = myApi.findByRaza(url.get(i));
            try {
                call.enqueue(this);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
      }
    }



    @Override
    public void onResponse(Call<RazasResponse>call, Response<RazasResponse> response) {
        try {

            if (response.isSuccessful()) {
                System.out.println("obteniendo imagenes por raza");
                RazasResponse ar = response.body();

                fotosRazas.add(ar.getMessage());

                System.out.println(fotosRazas.toString());


            }
            else
                fotosRazas.add("https://m.media-amazon.com/images/I/61FQCSP7ZIL._SS500_.jpg");
                System.out.println("error" + response.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onFailure(Call<RazasResponse> call, Throwable t) {
        System.out.println("error"+ t.toString());
    }

    public void cargarAdapter(){


        adaptador= new Adapter(arr,fotosRazas);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adaptador);


    }

    public ArrayList<String> trimDatosJson2(String raza, Context context){

        raza = raza.replaceAll("[^\\w\\.@-]", "");
        //  System.out.println(raza + "RAZA AQUI");
        String url= raza+"/images/random";


        array2= new ArrayList<>();
        array2.add(url);
        Intent intent = new Intent(context, ImagenesActivity.class);
        intent.putExtra("URL_ARRAY", array2);
        //llamada(array2);

        System.out.println(array2.get(0).toString());
        return array2;


    }

    public String trimDatosJson(String raza){

        raza = raza.replaceAll("[^\\w\\.@-]", "");
      //  System.out.println(raza + "RAZA AQUI");
        url= raza+"/images/random";

        return url;

    }
    public void sendParam(View view, String url) {


        Intent intent = new Intent(getApplicationContext(), ImagenesActivity.class);
        intent.putExtra("URL_ARRAY", url);

        startActivity(intent);
    }


}







