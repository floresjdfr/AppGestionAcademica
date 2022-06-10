package com.example.gestionacademicaapp.ui.career

import com.example.gestionacademicaapp.models.Career
import com.example.gestionacademicaapp.models.Response

class CareerRepository {
    lateinit var viewModel: CareerVM
    private val apiUrl = "http://10.0.2.2:5000/api/Career/"

    suspend fun fetchCareers(): Response {
        var response = Response()

        var careerList = ArrayList<Career>()
        careerList.add(Career(1, "IS", "Ingeniería en Sistemas", "Bachillerato en Ingeniería en Sistemas"))
        careerList.add(Career(2, "PA", "Producción Audiovisual", "Bachillerato en Producción Audiovisual"))

        response.Code = 1
        response.Content = careerList

        return response
    }
}