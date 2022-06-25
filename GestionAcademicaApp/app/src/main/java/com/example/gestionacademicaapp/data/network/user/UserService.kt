package com.example.gestionacademicaapp.data.network.user

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.user.UserModel
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

    suspend fun updateUser(id: Int, user: UserModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UserApiClient::class.java).updateUser(id, user)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteUser(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(UserApiClient::class.java).deleteUser(id)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}