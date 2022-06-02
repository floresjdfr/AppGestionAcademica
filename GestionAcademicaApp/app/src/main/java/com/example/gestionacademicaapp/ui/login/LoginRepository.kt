package com.example.gestionacademicaapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.ajax.Ajax
import com.example.gestionacademicaapp.ajax.AjaxMethod
import com.example.gestionacademicaapp.ajax.HttpResponse
import com.example.gestionacademicaapp.ajax.SuperAjax
import com.example.gestionacademicaapp.models.User
import com.google.gson.Gson
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection.*

class LoginRepository : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData(User())
    private val apiUrl = "http://192.168.100.22:45061/api/User/Login/"
    var isLogged = false

    fun getUser(): LiveData<User>{
        return _user
    }
    fun setUser(user: User){
        _user.value = user
    }

    suspend fun login(): Boolean {
        var loggedUser: User? = null
        isLogged = false

        val httpResponse = SuperAjax(apiUrl, AjaxMethod.POST, bodyJson = Gson().toJson(_user.value)).execute()

        if (httpResponse.responseCode == HTTP_OK) {
            loggedUser = Gson().fromJson(httpResponse.responseBody, User::class.java)
            _user.postValue(loggedUser)
            isLogged = true
            return true
        }
        return false
    }
}