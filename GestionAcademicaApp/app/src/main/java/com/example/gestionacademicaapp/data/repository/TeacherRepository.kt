package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.TeacherModel
import com.example.gestionacademicaapp.data.network.teacher.TeacherService

class TeacherRepository {
    companion object {
        private val api = TeacherService()
        suspend fun getTeachers(): ArrayList<TeacherModel> {
            return api.getTeachers()
        }

        suspend fun createTeacher(teacher: TeacherModel): Boolean {
            return api.createTeacher(teacher)
        }

        suspend fun deleteTeacher(id: Int): Boolean {
            return api.deleteTeacher(id)
        }

        suspend fun updateTeacher(id: Int, teacher: TeacherModel): Boolean {
            return api.updateTeacher(id, teacher)
        }
    }
}