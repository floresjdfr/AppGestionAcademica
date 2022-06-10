package com.example.gestionacademicaapp.ui.login

import com.example.gestionacademicaapp.ajax.AjaxMethod
import com.example.gestionacademicaapp.ajax.SuperAjax
import com.example.gestionacademicaapp.models.User
import com.google.gson.Gson
import java.net.HttpURLConnection.HTTP_OK
import com.example.gestionacademicaapp.models.Error
import com.example.gestionacademicaapp.models.HttpResponse
import com.example.gestionacademicaapp.models.Response


class LoginRepository {
    lateinit var viewModel: LoginVM
    private val apiUrl = "http://10.0.2.2:5000/api/User/Login"

    suspend fun login(): Response {
        val response = Response()
        val loggedUser: User?
        viewModel.isLogged = false

        val properties = ArrayList<Pair<String, String>>()
        properties.add(Pair("Content-Type", "application/json"))
        properties.add(Pair("Accept", "application/json"))
        val json = Gson().toJson(viewModel.getUser().value)

        val httpResponse =
            SuperAjax(apiUrl, AjaxMethod.POST, properties = properties, bodyJson = json).execute()

        if (httpResponse.javaClass.simpleName == Error::class.simpleName) {
            val apiResponse = httpResponse as Error
            response.Code = -1
            response.Content = apiResponse
        } else {
            val apiResponse = httpResponse as HttpResponse
            if (apiResponse.responseCode == HTTP_OK) {
                loggedUser = Gson().fromJson(httpResponse.responseBody, User::class.java)

                viewModel.setUser(loggedUser)
                viewModel.isLogged = true

                response.Code = 1
                response.Content = loggedUser
            } else { // Wrong username or password
                response.Code = 1
                response.Content = null
            }
        }
        return response
    }
}