package com.example.gestionacademicaapp.data.network

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun login(user: UserModel): UserModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UserApiClient::class.java).login(user)
            if (response.isSuccessful)
                response.body()
            else
                null
        }
    }
}