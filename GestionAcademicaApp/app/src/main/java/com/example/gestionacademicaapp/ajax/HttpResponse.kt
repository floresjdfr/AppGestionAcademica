package com.example.gestionacademicaapp.ajax

class HttpResponse {
    var responseCode: Int? = null
    var responseBody: String? = null

    constructor(){}
    constructor(responseCode: Int, responseBody: String){
        this.responseCode = responseCode
        this.responseBody = responseBody
    }
}