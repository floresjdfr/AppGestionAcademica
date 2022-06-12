package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CourseModel
import com.example.gestionacademicaapp.data.network.CourseService

class CourseRepository {
    companion object{
        private val api = CourseService()
        suspend fun getCourses(): ArrayList<CourseModel>{
            var response = api.getCourses()

            return response
        }
        suspend fun createCourse(career: CourseModel): Boolean{
            return api.createCourse(career)
        }

        suspend fun deleteCourse(id: Int): Boolean{
            return api.deleteCourse(id)
        }

        suspend fun updateCourse(id: Int, career: CourseModel): Boolean{
            return api.updateCourse(id, career)
        }
    }
}