package com.example.gestionacademicaapp.data.model

import java.time.LocalDate

data class CycleModel(override var ID: Int, var Year: Int, var CycleNumber: Int, var StartDate: LocalDate, var EndDate: LocalDate, var CycleState: CycleStateModel): BaseModel(), java.io.Serializable