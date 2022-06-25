package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.network.user.UserService

class UserRepository {
    companion object{
        private val api = UserService()

        suspend fun login(user: UserModel): UserModel?{
            return api.login(user)
        }

        suspend fun deleteUser(id: Int): Boolean {
            return UserRepository.api.deleteUser(id)
        }

        suspend fun updateUser(id: Int, user: UserModel): Boolean {
            return UserRepository.api.updateUser(id, user)
        }
    }
}