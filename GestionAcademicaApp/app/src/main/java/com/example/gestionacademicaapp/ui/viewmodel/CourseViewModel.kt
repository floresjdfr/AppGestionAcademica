package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.repository.CareerCoursesRepository
import com.example.gestionacademicaapp.data.repository.CourseRepository

class CourseViewModel : ViewModel() {
    val careerCourses = MutableLiveData<ArrayList<CareerCourseModel>>(ArrayList())
    val isLoading = MutableLiveData<Boolean>()
    suspend fun getCareerCourses(id: Int) {
        isLoading.postValue(true)
        val result = CareerCoursesRepository.getCareerCourses(id)
        careerCourses.postValue(result)
        isLoading.postValue(false)
    }

    suspend fun createCareerCourse(careerCourseModel: CareerCourseModel): Boolean {
        isLoading.postValue(true)
        var result = CareerCoursesRepository.createCareerCourse(careerCourseModel)
        isLoading.postValue(false)
        return result
    }

    suspend fun deleteCareerCourse(careerCourseModel: CareerCourseModel): Boolean {
        return CareerCoursesRepository.deleteCareerCourse(careerCourseModel.ID, careerCourseModel.Course.ID)
    }

    suspend fun updateCareerCourse(careerCourseModel: CareerCourseModel): Boolean {
        isLoading.postValue(true)
        var result = CareerCoursesRepository.updateCareerCourse(careerCourseModel.ID, careerCourseModel)
        isLoading.postValue(false)
        return result
    }
}