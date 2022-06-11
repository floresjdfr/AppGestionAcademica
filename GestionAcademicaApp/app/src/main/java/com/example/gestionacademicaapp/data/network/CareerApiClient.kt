package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CareerApiClient {
    @GET("Career")
    suspend fun getCareers(): Response<ArrayList<CareerModel>>
}