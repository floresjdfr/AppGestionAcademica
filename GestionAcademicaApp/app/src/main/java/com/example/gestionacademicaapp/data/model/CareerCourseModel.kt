package com.example.gestionacademicaapp.data.model

data class CareerCourseModel(
    override var ID: Int,
    var Year: Int,
    var Cycle: Int,
    var Course: CourseModel,
    var Career: CareerModel,
) : Base(), java.io.Serializable