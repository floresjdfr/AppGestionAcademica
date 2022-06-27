package com.example.gestionacademicaapp.data.network.group

import com.example.gestionacademicaapp.data.model.GroupModel
import retrofit2.Response
import retrofit2.http.*

interface GroupApiClient {
    @GET("Group/")
    suspend fun getGroups(): Response<ArrayList<GroupModel>>

    @POST("Group/")
    suspend fun createGroup(@Body group: GroupModel): Response<Boolean>

    @DELETE("Group/{id}")
    suspend fun deleteGroup(@Path("id") id: Int): Response<Boolean>

    @PUT("Group/{id}")
    suspend fun updateGroup(@Path("id") id: Int, @Body group: GroupModel): Response<Boolean>
}