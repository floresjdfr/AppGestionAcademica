package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.*

interface CareerApiClient {
    @GET("Career")
    suspend fun getCareers(): Response<ArrayList<CareerModel>>

    @POST("Career")
    suspend fun createCareer(@Body career: CareerModel): Response<Boolean>

    @DELETE("Career/{id}")
    suspend fun deleteCareer(@Path("id") id: Int): Response<Boolean>

    @PUT("Career/{id}")
    suspend fun updateCareer(@Path("id") id: Int, @Body career: CareerModel):Response<Boolean>
}