package com.example.gestionacademicaapp.data.network.group

import com.example.gestionacademicaapp.core.RetrofitHelper
import com.example.gestionacademicaapp.data.model.GroupModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroupService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getGroups(): ArrayList<GroupModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(GroupApiClient::class.java)
                .getGroups()
            if (response.isSuccessful)
                response.body()!!
            else
                ArrayList()
        }
    }

    suspend fun createGroup(group: GroupModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit
                .create(GroupApiClient::class.java)
                .createGroup(group)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun deleteGroup(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(GroupApiClient::class.java).deleteGroup(id)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }

    suspend fun updateGroup(id: Int, group: GroupModel): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(GroupApiClient::class.java).updateGroup(id, group)
            if (response.isSuccessful)
                response.body()!!
            else
                false
        }
    }
}