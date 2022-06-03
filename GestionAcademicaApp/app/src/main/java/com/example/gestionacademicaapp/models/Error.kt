package com.example.gestionacademicaapp.models

class Error constructor() : Base(), java.io.Serializable{
    var ErrorMessage: String? = null

    constructor(errorMessage: String): this(){
        this.ErrorMessage = errorMessage
    }
}