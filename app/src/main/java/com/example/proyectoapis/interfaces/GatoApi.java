package com.example.proyectoapis.interfaces;

import com.example.proyectoapis.models.Gato;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GatoApi {
    @GET("facts/{id}}")
    public Call<Gato> find(@Path("id")String id);
}
