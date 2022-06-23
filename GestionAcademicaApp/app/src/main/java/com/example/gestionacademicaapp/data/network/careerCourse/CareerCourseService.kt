package com.example.gestionacademicaapp.data.network.careerCourse

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CareerCourseModel
import com.example.gestionacademicaapp.data.network.course.CourseApiClient
import com.example.gestionacademicaapp.data.network.cycle.CycleApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CareerCourseService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCareerCourses(id: Int): ArrayList<CareerCourseModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CareerCourseApiClient::class.java).getCareerCourses(id)
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }

    suspend fun createCareerCourse(careerCourse: CareerCourseModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CareerCourseApiClient::class.java).createCareerCourse(careerCourse)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteCareerCourse(idCareerCourse: Int, idCourse: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val responseCareerCourse =
                retrofit.create(CareerCourseApiClient::class.java).deleteCareerCourse(idCareerCourse)
            var responseCourse: Response<Boolean>? = null

            if (responseCareerCourse.isSuccessful) {
                responseCourse = retrofit.create(CourseApiClient::class.java).deleteCourse(idCourse)
                if (responseCourse.isSuccessful)
                    responseCourse.body()!!
                else
                    false
            } else
                false
        }
    }

    suspend fun updateCareerCourse(careerCourse: CareerCourseModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(CareerCourseApiClient::class.java).updateCareerCourse(careerCourse.ID, careerCourse)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}