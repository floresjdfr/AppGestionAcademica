package com.example.gestionacademicaapp.data.network.cycle

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CycleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CycleService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCycles(): ArrayList<CycleModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CycleApiClient::class.java).getCycles()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    
    suspend fun getCycleById(id: Int): CycleModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CycleApiClient::class.java).getCycleById(id)
            if (response.isSuccessful)
                response.body()!!
            else
                null
        }
    }
    suspend fun createCycle(cycle: CycleModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CycleApiClient::class.java).createCycle(cycle)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteCycle(id: Int): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CycleApiClient::class.java).deleteCycle(id)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateCycle(id: Int, cycle: CycleModel): Boolean{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(CycleApiClient::class.java).updateCycle(id, cycle)
            if(response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}