package com.example.gestionacademicaapp.data.network.teacher

import com.example.gestionacademicaapp.data.model.TeacherModel
import retrofit2.Response
import retrofit2.http.*

interface TeacherApiClient {
    @GET("Teacher/")
    suspend fun getTeachers(): Response<ArrayList<TeacherModel>>

    @POST("Teacher/")
    suspend fun createTeacher(@Body teacher: TeacherModel): Response<Boolean>

    @DELETE("Teacher/{id}")
    suspend fun deleteTeacher(@Path("id") id: Int): Response<Boolean>

    @PUT("Teacher/{id}")
    suspend fun updateTeacher(@Path("id") id: Int, @Body teacher: TeacherModel):Response<Boolean>
}