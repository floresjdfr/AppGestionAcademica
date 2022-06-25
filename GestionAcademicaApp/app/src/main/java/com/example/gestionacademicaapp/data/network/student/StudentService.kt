package com.example.gestionacademicaapp.data.network.student

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getStudents(): ArrayList<StudentModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(StudentApiClient::class.java)
                .getStudents()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }

    suspend fun createStudent(student: StudentModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(StudentApiClient::class.java)
                .createStudent(student)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteStudent(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(StudentApiClient::class.java).deleteStudent(id)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateStudent(id: Int, student: StudentModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(StudentApiClient::class.java).updateStudent(id, student)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}