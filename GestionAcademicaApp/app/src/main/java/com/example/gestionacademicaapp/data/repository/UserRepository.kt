package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.UserModel
import com.example.gestionacademicaapp.data.network.UserService

class UserRepository {
    companion object{
        private val api = UserService()

        suspend fun login(user: UserModel): UserModel?{
            return api.login(user)
        }
    }
}