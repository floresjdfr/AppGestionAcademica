package com.example.gestionacademicaapp.data.network.course

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.network.cycle.CycleApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCourses(id: Int): ArrayList<CourseModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseApiClient::class.java).getCourses()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    suspend fun createCourse(course: CourseModel): Boolean {
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

    suspend fun updateCourse(id: Int, course: CourseModel): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CourseApiClient::class.java).updateCourse(id, course)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}