package com.example.gestionacademicaapp.data.network.careerCourse

import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CareerModel
import retrofit2.Response
import retrofit2.http.*

interface CareerCourseApiClient {
    @GET("CareerCourses/GetByCareer/{id}")
    suspend fun getCareerCourses(@Path("id") id: Int): Response<ArrayList<CareerCourseModel>>

    @POST("CareerCourses/")
    suspend fun createCareerCourse(@Body course: CareerCourseModel): Response<Boolean>

    @DELETE("CareerCourses/{id}")
    suspend fun deleteCareerCourse(@Path("id") id: Int): Response<Boolean>

    @PUT("CareerCourses/{id}")
    suspend fun updateCareerCourse(@Path("id") id: Int, @Body careerCourse: CareerCourseModel):Response<Boolean>
}