package com.example.gestionacademicaapp.models

class UserType: Base() {
    var TypeDescription: String? = null
}

enum class EnumUserType{
    Administrador , Matriculador, Profesor, Alumno
}