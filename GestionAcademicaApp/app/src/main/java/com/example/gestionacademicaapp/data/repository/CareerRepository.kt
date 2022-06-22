package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.CareerProvider
import com.example.gestionacademicaapp.data.network.career.CareerService

class CareerRepository {
    companion object{
        private val api = CareerService()
        suspend fun getCareers(): ArrayList<CareerModel>{
            var response = api.getCareers()
            CareerProvider.careers = response
            return response
        }
        suspend fun createCareer(career: CareerModel): Boolean{
            return api.createCareer(career)
        }

        suspend fun deleteCareer(id: Int): Boolean{
            return api.deleteCareer(id)
        }

        suspend fun updateCareer(id: Int, career: CareerModel): Boolean{
            return api.updateCareer(id, career)
        }
    }
}