package com.example.gestionacademicaapp.models

class HttpResponse constructor() : Base() {
    var responseCode: Int? = null
    var responseBody: String? = null

    constructor(responseCode: Int, responseBody: String) : this(){
        this.responseCode = responseCode
        this.responseBody = responseBody
    }
}