package com.example.gestionacademicaapp.data.network.courseGroup

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CourseGroupModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseGroupService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCourseGroups(id: Int): ArrayList<CourseGroupModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(CourseGroupApiClient::class.java)
                .getCourseGroups(id)
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }

    suspend fun createCourseGroup(courseGroup: CourseGroupModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(CourseGroupApiClient::class.java)
                .createCourseGroup(courseGroup)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteCourseGroup(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseGroupApiClient::class.java).deleteCourseGroup(id)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateCourseGroup(id: Int, courseGroup: CourseGroupModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CourseGroupApiClient::class.java).updateCourseGroup(id, courseGroup)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}