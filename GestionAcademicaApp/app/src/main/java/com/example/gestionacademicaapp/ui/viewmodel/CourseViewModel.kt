package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.repository.CourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    val courses = MutableLiveData<ArrayList<CourseModel>>(ArrayList())
    val isLoading = MutableLiveData<Boolean>()
    suspend fun getCourses(id: Int) {
        isLoading.postValue(true)
        val result = CourseRepository.getCourses(id)
        courses.postValue(result)
//        courses.value = result
        isLoading.postValue(false)
    }

    suspend fun createCourse(careerCourseModel: CareerCourseModel) {
        isLoading.postValue(true)
        var result = CourseRepository.createCourse(careerCourseModel)
        isLoading.postValue(false)
    }

    suspend fun deleteCourse(id: Int): Boolean {
        return CourseRepository.deleteCourse(id)
    }

    suspend fun updateCourse(id: Int, career: CourseModel): Boolean {
        isLoading.postValue(true)
        var result = CourseRepository.updateCourse(id, career)
        isLoading.postValue(false)
        return result
    }
}