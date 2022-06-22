package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val user = MutableLiveData<UserModel>()
    val isLogged = MutableLiveData<Boolean>()

    fun login(user: UserModel) {
        viewModelScope.launch {
            var result = UserRepository.login(user)
            if(result != null)
                isLogged.postValue(true)
            else
                isLogged.postValue(false)
        }
    }
}