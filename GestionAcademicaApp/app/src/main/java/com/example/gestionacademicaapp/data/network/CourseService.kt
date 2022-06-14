package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCourses(id: Int): ArrayList<CareerCourseModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseApiClient::class.java).getCourses(id)
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    suspend fun createCourse(course: CareerCourseModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseApiClient::class.java).createCourse(course)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteCourse(id: Int): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CourseApiClient::class.java).deleteCourse(id)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateCourse(id: Int, career: CourseModel): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CourseApiClient::class.java).updateCourse(id, career)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}