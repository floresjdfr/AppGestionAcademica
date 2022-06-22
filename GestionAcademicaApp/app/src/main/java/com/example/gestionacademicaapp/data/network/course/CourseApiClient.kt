package com.example.gestionacademicaapp.data.network.course

import com.example.gestionacademicaapp.data.model.CourseModel
import retrofit2.Response
import retrofit2.http.*

interface CourseApiClient {
    @GET("Course/")
    suspend fun getCourses(): Response<ArrayList<CourseModel>>

    @POST("Course/")
    suspend fun createCourse(@Body course: CourseModel): Response<Boolean>

    @DELETE("Course/{id}")
    suspend fun deleteCourse(@Path("id") id: Int): Response<Boolean>

    @PUT("Course/{id}")
    suspend fun updateCourse(@Path("id") id: Int, @Body course: CourseModel):Response<Boolean>
}