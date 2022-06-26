package com.example.gestionacademicaapp.data.model

import com.example.gestionacademicaapp.data.model.cycle.CycleModel

data class GroupModel(
    override var ID: Int,
    var Number: String,
    var Schedule: String,
    var Teacher: TeacherModel,
    var Cycle: CycleModel,
    var Course: CourseModel,
) :
    BaseModel(), java.io.Serializable
