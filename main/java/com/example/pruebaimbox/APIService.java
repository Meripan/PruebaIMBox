package com.example.pruebaimbox;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    public Call <RazasResponse> findByRaza(@Url String url);




}
