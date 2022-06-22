package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.network.careerCourse.CareerCourseService

class CareerCoursesRepository {
    companion object {
        private val api = CareerCourseService()
        suspend fun getCareerCourses(id: Int): ArrayList<CareerCourseModel> {
            return api.getCareerCourses(id)
        }

        suspend fun createCareerCourse(careerCourse: CareerCourseModel): Boolean {
            return api.createCareerCourse(careerCourse)
        }

        suspend fun deleteCareerCourse(idCareerCourse: Int, idCourse: Int): Boolean {
            return api.deleteCareerCourse(idCareerCourse, idCourse)
        }

        suspend fun updateCareerCourse(id: Int, careerCourse: CareerCourseModel): Boolean {
            return api.updateCareerCourse(careerCourse)
        }
    }
}