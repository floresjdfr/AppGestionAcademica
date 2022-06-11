package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.UserModel
import com.example.gestionacademicaapp.data.network.CareerService
import com.example.gestionacademicaapp.data.network.UserService

class CareerRepository {
    companion object{
        private val api = CareerService()
        suspend fun getCareers(): ArrayList<CareerModel>{
            return api.getCareers()
        }
    }
}