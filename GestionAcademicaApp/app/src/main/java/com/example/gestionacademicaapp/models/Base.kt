package com.example.gestionacademicaapp.models

open class Base: java.io.Serializable{
    var ID: Int? = null
    constructor(){}
    constructor(id: Int){
        this.ID = id
    }

}