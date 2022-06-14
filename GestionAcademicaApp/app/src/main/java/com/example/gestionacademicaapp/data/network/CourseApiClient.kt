package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CourseModel
import retrofit2.Response
import retrofit2.http.*

interface CourseApiClient {
    @GET("CareerCourses/GetByCareer/{id}")
    suspend fun getCourses(@Path("id") id: Int): Response<ArrayList<CareerCourseModel>>

    @POST("CareerCourses/")
    suspend fun createCourse(@Body course: CareerCourseModel): Response<Boolean>

    @DELETE("Course/{id}")
    suspend fun deleteCourse(@Path("id") id: Int): Response<Boolean>

    @PUT("Course/{id}")
    suspend fun updateCourse(@Path("id") id: Int, @Body career: CourseModel):Response<Boolean>
}