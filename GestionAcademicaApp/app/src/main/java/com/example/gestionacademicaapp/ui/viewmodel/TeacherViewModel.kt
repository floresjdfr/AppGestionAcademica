package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.TeacherModel
import com.example.gestionacademicaapp.data.repository.TeacherRepository

class TeacherViewModel : ViewModel() {

    val teachers = MutableLiveData<ArrayList<TeacherModel>>(ArrayList())
    val teacher = MutableLiveData<TeacherModel>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun getTeachers() {
        isLoading.postValue(true)
        val result = TeacherRepository.getTeachers()
        teachers.postValue(result)
        isLoading.postValue(false)
    }

    suspend fun createTeacher(teacher: TeacherModel): Boolean {
        isLoading.postValue(true)
        val result = TeacherRepository.createTeacher(teacher)
        isLoading.postValue(false)
        return result
    }

    suspend fun deleteTeacher(teacher: TeacherModel): Boolean {
        isLoading.postValue(true)
        val result = TeacherRepository.deleteTeacher(teacher.ID)
        isLoading.postValue(false)
        return result
    }

    suspend fun updateTeacher(teacher: TeacherModel): Boolean {
        isLoading.postValue(true)
        val result = TeacherRepository.updateTeacher(teacher.ID, teacher)
        isLoading.postValue(false)
        return result
    }
}