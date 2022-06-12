package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCourses(): ArrayList<CourseModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseApiClient::class.java).getCourses()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    suspend fun createCourse(career: CourseModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseApiClient::class.java).createCourse(career)
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