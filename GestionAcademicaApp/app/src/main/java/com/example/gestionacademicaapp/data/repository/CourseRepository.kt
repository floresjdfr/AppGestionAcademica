package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.network.cycle.CycleService

class CourseRepository {
    companion object {
        private val api = CycleService()
        suspend fun getCourses(id: Int): ArrayList<CourseModel> {
            return api.getCourses(id)
        }

        suspend fun createCourse(course: CourseModel): Boolean {
            return api.createCourse(course)
        }

        suspend fun deleteCourse(id: Int): Boolean {
            return api.deleteCourse(id)
        }

        suspend fun updateCourse(id: Int, course: CourseModel): Boolean {
            return api.updateCourse(id, course)
        }
    }
}