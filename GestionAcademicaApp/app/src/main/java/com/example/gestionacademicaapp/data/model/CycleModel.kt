package com.example.gestionacademicaapp.data.model

import java.util.*

data class CycleModel(override var ID: Int, var Year: Int, var Number: Int, var StartDate: Date, var EndDate: Date, var CycleState: CycleStateModel): BaseModel(), java.io.Serializable