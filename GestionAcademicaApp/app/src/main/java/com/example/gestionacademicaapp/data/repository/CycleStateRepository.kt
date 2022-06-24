package com.example.gestionacademicaapp.data.repository

import com.example.gestionacademicaapp.data.model.cycle.CycleStateModel
import com.example.gestionacademicaapp.data.network.cycle.CycleStateService

class CycleStateRepository {
    companion object {
        private val api = CycleStateService()
        suspend fun getCycleStates(): ArrayList<CycleStateModel> {
            return api.getCycleState()
        }

        suspend fun getCycleState(id: Int): CycleStateModel? {
            return api.getCycleState(id)
        }
    }
}