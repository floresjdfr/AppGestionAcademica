package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.StudentModel
import com.example.gestionacademicaapp.data.network.student.StudentService

class StudentRepository {
    companion object {
        private val api = StudentService()
        suspend fun getStudents(): ArrayList<StudentModel> {
            return api.getStudents()
        }

        suspend fun createStudent(student: StudentModel): Boolean {
            return api.createStudent(student)
        }

        suspend fun deleteStudent(id: Int): Boolean {
            return api.deleteStudent(id)
        }

        suspend fun updateStudent(id: Int, student: StudentModel): Boolean {
            return api.updateStudent(id, student)
        }
    }
}