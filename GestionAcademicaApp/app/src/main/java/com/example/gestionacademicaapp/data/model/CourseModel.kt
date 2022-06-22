package com.example.gestionacademicaapp.data.model

data class CourseModel(override var ID: Int, var Code: String, var Name: String, var Credits: Int, var WeeklyHours: Int): BaseModel(), java.io.Serializable