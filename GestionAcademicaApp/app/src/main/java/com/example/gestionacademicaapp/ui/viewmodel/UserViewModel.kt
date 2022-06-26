package com.example.gestionacademicaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionacademicaapp.data.model.user.EnumUserType
import com.example.gestionacademicaapp.data.model.user.UserModel
import com.example.gestionacademicaapp.data.model.user.UserType
import com.example.gestionacademicaapp.data.repository.UserRepository

class UserViewModel : ViewModel() {

    val users = MutableLiveData<ArrayList<UserModel>>(ArrayList())
    val userTypes = MutableLiveData<ArrayList<UserType>>(ArrayList())
    val user = MutableLiveData<UserModel>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun getUsers() {
        isLoading.postValue(true)
        val result = UserRepository.getUsers()
        users.postValue(result)
        isLoading.postValue(false)
    }

    fun getUserTypes() {
        val types = arrayListOf<UserType>()
        val values = EnumUserType.values()
        for (item in values)
            types.add(UserType(item.id, item.toString()))

        userTypes.postValue(types)
    }
    suspend fun createUser(user: UserModel): Boolean {
        isLoading.postValue(true)
        val result = UserRepository.createUser(user)
        isLoading.postValue(false)
        return result
    }

    suspend fun deleteUser(user: UserModel): Boolean {
        var result = false
        isLoading.postValue(true)

        result = UserRepository.deleteUser(user.ID)
        if (result)//UserDeleted
            UserRepository.deleteUser(user.ID!!)//Then delete user

        isLoading.postValue(false)
        return result
    }

    suspend fun updateUser(user: UserModel): Boolean {
        isLoading.postValue(true)
        val result = UserRepository.updateUser(user.ID, user)
        isLoading.postValue(false)
        return result
    }
}