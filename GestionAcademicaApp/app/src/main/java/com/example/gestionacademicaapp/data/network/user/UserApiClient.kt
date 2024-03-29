package com.example.gestionacademicaapp.data.network.user

import com.example.gestionacademicaapp.data.model.user.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserApiClient {

    @GET("User")
    suspend fun getUsers(): Response<ArrayList<UserModel>>

    @POST("User/Login")
    suspend fun login(@Body user: UserModel): Response<UserModel>

    @POST("User")
    suspend fun createUser(@Body user: UserModel): Response<Boolean>

    @DELETE("User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Boolean>

    @PUT("User/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserModel): Response<Boolean>

}