package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionacademicaapp.data.model.CareerModel
import com.example.gestionacademicaapp.data.repository.CareerRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CareerViewModel : ViewModel() {
    val careers = MutableLiveData<ArrayList<CareerModel>>(ArrayList())
    val isLoading = MutableLiveData<Boolean>()
    fun getCareers() {
        viewModelScope.launch{
            isLoading.postValue(true)
            var result = CareerRepository.getCareers()
            careers.postValue(result)
            isLoading.postValue(false)
        }
    }
}