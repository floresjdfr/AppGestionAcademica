package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.CycleModel
import com.example.gestionacademicaapp.data.network.cycle.CycleService

class CycleRepository {
    companion object {
        private val api = CycleService()
        suspend fun getCycles(): ArrayList<CycleModel> {
            return api.getCycles()
        }

        suspend fun getCycleById(id: Int): CycleModel? {
            return api.getCycleById(id)
        }

        suspend fun createCycle(course: CycleModel): Boolean {
            return api.createCycle(course)
        }

        suspend fun deleteCycle(id: Int): Boolean {
            return api.deleteCycle(id)
        }

        suspend fun updateCycle(id: Int, course: CycleModel): Boolean {
            return api.updateCycle(id, course)
        }
    }
}