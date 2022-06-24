package com.example.gestionacademicaapp.data.network.cycle

import com.example.gestionacademicaapp.data.model.cycle.CycleStateModel
import retrofit2.Response
import retrofit2.http.*

interface CycleStateApiClient {
    @GET("CycleState/")
    suspend fun getCycleStates(): Response<ArrayList<CycleStateModel>>

    @GET("CycleState/{id}")
    suspend fun getCycleStatesById(@Path("id") id: Int): Response<CycleStateModel>
}