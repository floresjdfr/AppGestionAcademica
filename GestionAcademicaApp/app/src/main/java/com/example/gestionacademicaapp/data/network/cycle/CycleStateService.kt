package com.example.gestionacademicaapp.data.network.cycle

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.CycleStateModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CycleStateService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCycleState(): ArrayList<CycleStateModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CycleStateApiClient::class.java).getCycleStates()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }
    suspend fun getCycleState(id: Int): CycleStateModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CycleStateApiClient::class.java).getCycleStatesById(id)
            if (response.isSuccessful)
                response.body()!!
            else
                null
        }
    }
}