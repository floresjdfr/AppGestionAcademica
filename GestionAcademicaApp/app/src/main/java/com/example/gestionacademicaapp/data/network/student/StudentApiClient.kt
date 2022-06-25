package com.example.gestionacademicaapp.data.network.student

import com.example.gestionacademicaapp.data.model.StudentModel
import retrofit2.Response
import retrofit2.http.*

interface StudentApiClient {
    @GET("Student/")
    suspend fun getStudents(): Response<ArrayList<StudentModel>>

    @POST("Student/")
    suspend fun createStudent(@Body student: StudentModel): Response<Boolean>

    @DELETE("Student/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Boolean>

    @PUT("Student/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: StudentModel): Response<Boolean>
}