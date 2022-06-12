package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CareerService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCareers(): ArrayList<CareerModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CareerApiClient::class.java).getCareers()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    suspend fun createCareer(career: CareerModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CareerApiClient::class.java).createCareer(career)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteCareer(id: Int): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CareerApiClient::class.java).deleteCareer(id)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateCareer(id: Int, career: CareerModel): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CareerApiClient::class.java).updateCareer(id, career)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}