package com.example.gestionacademicaapp.models

class UserType : Base, java.io.Serializable {
    var TypeDescription: String? = null

    constructor(){}
    constructor(typeDescription: String) : this(){
        this.TypeDescription = typeDescription
    }
}

enum class EnumUserType{
    Administrador , Matriculador, Profesor, Alumno
}