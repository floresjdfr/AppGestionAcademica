package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.network.CourseService

class CourseRepository {
    companion object {
        private val api = CourseService()
        suspend fun getCourses(id: Int): ArrayList<CourseModel> {
            val response = api.getCourses(id)
            val coursesList = ArrayList<CourseModel>()
            response.forEach {
                coursesList.add(it.Course)
            }
            return coursesList
        }

        suspend fun createCourse(careerCourse: CareerCourseModel): Boolean {
            return api.createCourse(careerCourse)
        }

        suspend fun deleteCourse(id: Int): Boolean {
            return api.deleteCourse(id)
        }

        suspend fun updateCourse(id: Int, career: CourseModel): Boolean {
            return api.updateCourse(id, career)
        }
    }
}