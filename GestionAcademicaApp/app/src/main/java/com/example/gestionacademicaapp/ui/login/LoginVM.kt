package com.example.gestionacademicaapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.models.User

class LoginVM : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData(User())
    var isLogged = false

    fun getUser(): LiveData<User> {
        return _user
    }
    fun setUser(user: User){
        _user.postValue(user)
    }
}