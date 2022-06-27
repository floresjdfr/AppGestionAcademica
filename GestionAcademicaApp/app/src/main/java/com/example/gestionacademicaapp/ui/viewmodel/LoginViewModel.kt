package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loggedUser = MutableLiveData<UserModel>()
    val isLogged = MutableLiveData<Boolean>()

    suspend fun login(user: UserModel) {
            val result = UserRepository.login(user)
            if(result != null){
                loggedUser.value = result
                isLogged.postValue(true)

            }
            else
                isLogged.postValue(false)
    }
}