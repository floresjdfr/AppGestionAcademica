package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiClient {
    @POST("User/Login")
    suspend fun login(@Body user: UserModel): Response<UserModel>
}