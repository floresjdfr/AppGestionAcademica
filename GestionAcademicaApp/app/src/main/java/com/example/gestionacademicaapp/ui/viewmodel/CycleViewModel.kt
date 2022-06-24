package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.cycle.CycleModel
import com.example.gestionacademicaapp.data.repository.CycleRepository

class CycleViewModel : ViewModel() {
    val cycles = MutableLiveData<ArrayList<CycleModel>>(ArrayList())
    val cycle = MutableLiveData<CycleModel>()
    val isLoading = MutableLiveData<Boolean>()
    suspend fun getCycles() {
        isLoading.postValue(true)
        val result = CycleRepository.getCycles()
        cycles.postValue(result)
        isLoading.postValue(false)
    }

    suspend fun getCycleById(id: Int) {
        isLoading.postValue(true)
        val result = CycleRepository.getCycleById(id)
        cycle.postValue(result)
        isLoading.postValue(false)
    }
    suspend fun createCycle(cycle: CycleModel): Boolean {
        isLoading.postValue(true)
        val result = CycleRepository.createCycle(cycle)
        isLoading.postValue(false)
        return result
    }

    suspend fun deleteCycle(cycle: CycleModel): Boolean {
        isLoading.postValue(true)
        val result = CycleRepository.deleteCycle(cycle.ID)
        isLoading.postValue(false)
        return result
    }

    suspend fun updateCycle(cycle: CycleModel): Boolean {
        isLoading.postValue(true)
        var result = CycleRepository.updateCycle(cycle.ID, cycle)
        isLoading.postValue(false)
        return result
    }
}