package com.example.gestionacademicaapp.data.network.teacher

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.TeacherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeacherService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTeachers(): ArrayList<TeacherModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(TeacherApiClient::class.java)
                .getTeachers()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }

    suspend fun createTeacher(teacher: TeacherModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(TeacherApiClient::class.java)
                .createTeacher(teacher)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteTeacher(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TeacherApiClient::class.java).deleteTeacher(id)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateTeacher(id: Int, teacher: TeacherModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(TeacherApiClient::class.java).updateTeacher(id, teacher)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}