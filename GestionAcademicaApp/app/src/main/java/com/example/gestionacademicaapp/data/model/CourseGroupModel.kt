package com.example.gestionacademicaapp.data.model

data class CourseGroupModel(override var ID: Int, var Course: CourseModel, var Group: GroupModel) : BaseModel(),
    java.io.Serializable
