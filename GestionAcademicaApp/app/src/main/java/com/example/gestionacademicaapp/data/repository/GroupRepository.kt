package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.GroupModel
import com.example.gestionacademicaapp.data.network.group.GroupService

class GroupRepository {


    companion object {
        private val api = GroupService()

        suspend fun getGroups(): ArrayList<GroupModel> {
            return api.getGroups()
        }

        suspend fun createGroup(group: GroupModel): Boolean {
            return api.createGroup(group)
        }

        suspend fun deleteGroup(id: Int): Boolean {
            return api.deleteGroup(id)
        }

        suspend fun updateGroup(id: Int, group: GroupModel): Boolean {
            return api.updateGroup(id, group)
        }
    }
}