package com.example.gestionacademicaapp.ui.career

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.models.Career

class CareerVM : ViewModel(){
    private val _careersList = MutableLiveData(ArrayList<Career>())

    fun getCareerList(): LiveData<ArrayList<Career>>{
        return _careersList
    }
    fun setCareerList(careerList: ArrayList<Career>){
        _careersList.value = careerList
    }
}