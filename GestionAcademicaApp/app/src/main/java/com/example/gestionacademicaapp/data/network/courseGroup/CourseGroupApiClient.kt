package com.example.gestionacademicaapp.data.network.courseGroup

import com.example.gestionacademicaapp.data.model.CourseGroupModel
import com.example.gestionacademicaapp.data.model.CourseModel
import retrofit2.Response
import retrofit2.http.*

interface CourseGroupApiClient {
    @GET("CourseGroups/GetByCourse/{id}")
    suspend fun getCourseGroups(@Path("id") id: Int): Response<ArrayList<CourseGroupModel>>

    @POST("CourseGroups/")
    suspend fun createCourseGroup(@Body courseGroup: CourseGroupModel): Response<Boolean>

    @DELETE("CourseGroups/{id}")
    suspend fun deleteCourseGroup(@Path("id") id: Int): Response<Boolean>

    @PUT("CourseGroups/{id}")
    suspend fun updateCourseGroup(@Path("id") id: Int, @Body courseGroup: CourseGroupModel): Response<Boolean>
}