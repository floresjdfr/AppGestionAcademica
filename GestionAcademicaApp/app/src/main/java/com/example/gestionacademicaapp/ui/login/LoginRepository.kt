package com.example.gestionacademicaapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.ajax.Ajax
import com.example.gestionacademicaapp.ajax.AjaxMethod
import com.example.gestionacademicaapp.ajax.HttpResponse
import com.example.gestionacademicaapp.models.User
import com.google.gson.Gson
import java.net.HttpURLConnection.*

class LoginRepository : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData(User("admin", "admin"))
    private val apiUrl = "http://192.168.100.22:45061/api/User/Login/"

    fun getUser(): LiveData<User>{
        return _user
    }
    fun setUser(user: User){
        _user.value = user
    }

    var isLogged = false

    fun login(callback: () -> Unit) {
        var loggedUser: User? = null
        isLogged = false

        Ajax(apiUrl, AjaxMethod.POST, bodyJson = Gson().toJson(_user.value)).execute {
            if (it.responseCode == HTTP_OK) {
                loggedUser = Gson().fromJson(it.responseBody, User::class.java)
                loggedUser?.UserID = "Just a Test"
                setUser(loggedUser!!)
                isLogged = true
            }
            callback.invoke()
        }

    }
}