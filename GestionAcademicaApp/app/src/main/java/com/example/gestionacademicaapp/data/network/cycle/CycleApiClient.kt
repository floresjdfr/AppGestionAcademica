package com.example.gestionacademicaapp.data.network.cycle

import com.example.gestionacademicaapp.data.model.CycleModel
import retrofit2.Response
import retrofit2.http.*

interface CycleApiClient {
    @GET("Cycle/")
    suspend fun getCycles(): Response<ArrayList<CycleModel>>

    @GET("Cycle/")
    suspend fun getCycleById(@Path("id") id: Int): Response<CycleModel>

    @POST("Cycle/")
    suspend fun createCycle(@Body cycle: CycleModel): Response<Boolean>

    @DELETE("Cycle/{id}")
    suspend fun deleteCycle(@Path("id") id: Int): Response<Boolean>

    @PUT("Cycle/{id}")
    suspend fun updateCycle(@Path("id") id: Int, @Body cycle: CycleModel):Response<Boolean>
}