package com.example.gestionacademicaapp.data.network.user

import com.example.gestionacademicaapp.data.model.user.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiClient {
    @POST("User/Login")
    suspend fun login(@Body user: UserModel): Response<UserModel>
}