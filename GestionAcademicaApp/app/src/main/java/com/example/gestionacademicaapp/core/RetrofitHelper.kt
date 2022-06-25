package com.example.gestionacademicaapp.core

import com.example.gestionacademicaapp.core.utils.RetrofitUtils
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object RetrofitHelper {
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    private fun getClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor())
        .callTimeout(Duration.ofSeconds(500))
        .build()

    fun getRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(RetrofitUtils.baseUri)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(getClient())
        .build()
}