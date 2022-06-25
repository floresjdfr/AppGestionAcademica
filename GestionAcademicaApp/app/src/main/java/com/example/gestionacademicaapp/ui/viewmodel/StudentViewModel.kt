package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.StudentModel
import com.example.gestionacademicaapp.data.repository.CareerRepository
import com.example.gestionacademicaapp.data.repository.StudentRepository
import com.example.gestionacademicaapp.data.repository.UserRepository

class StudentViewModel: ViewModel() {
    val students = MutableLiveData<ArrayList<StudentModel>>(ArrayList())
    val careers = MutableLiveData<ArrayList<CareerModel>>(ArrayList())
    val student = MutableLiveData<StudentModel>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun getStudents() {
        isLoading.postValue(true)
        val result = StudentRepository.getStudents()
        students.postValue(result)
        isLoading.postValue(false)
    }

    suspend fun createStudent(student: StudentModel): Boolean {
        isLoading.postValue(true)
        val result = StudentRepository.createStudent(student)
        isLoading.postValue(false)
        return result
    }

    suspend fun deleteStudent(student: StudentModel): Boolean {
        var result = false
        isLoading.postValue(true)

        result = StudentRepository.deleteStudent(student.ID)
        if(result)//StudentDeleted
            UserRepository.deleteUser(student.User?.ID!!)//Then delete user

        isLoading.postValue(false)
        return result
    }

    suspend fun updateStudent(student: StudentModel): Boolean {
        isLoading.postValue(true)
        val result = StudentRepository.updateStudent(student.ID, student)
        isLoading.postValue(false)
        return result
    }

    suspend fun getCareers() {
        isLoading.postValue(true)
        val result = CareerRepository.getCareers()
        careers.postValue(result)
        isLoading.postValue(false)
    }

}