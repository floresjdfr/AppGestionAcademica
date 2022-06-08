package com.example.gestionacademicaapp.models

class User : Base, java.io.Serializable {

    var UserType: UserType? = null
    var UserID: String? = null
    var Password: String? = null

    constructor() {}
    constructor(id: Int, username: String, password: String, userType: EnumUserType) {
        this.ID = id
        this.UserID = username
        this.Password = password
        UserType = UserType(userType.toString())
    }


}