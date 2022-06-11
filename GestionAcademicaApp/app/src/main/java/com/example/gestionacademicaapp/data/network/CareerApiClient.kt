package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareerApiClient {
    @GET("Career")
    suspend fun getCareers(): Response<ArrayList<CareerModel>>

    @POST("Career")
    suspend fun createCareer(@Body career: CareerModel): Response<Boolean>

    @DELETE("Career/{id}")
    suspend fun deleteCareer(@Path("id") id: Int): Response<Boolean>
}