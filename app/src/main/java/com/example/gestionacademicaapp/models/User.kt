package com.example.gestionacademicaapp.models

class User constructor(): Base() {
    var UserType: UserType? = null
    var UserID: String? = null
    var Password: String? = null

    constructor(username: String, password: String) : this() {
        this.UserID = username
        this.Password = password
    }


}