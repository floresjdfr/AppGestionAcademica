package com.example.gestionacademicaapp.data.model

import com.example.gestionacademicaapp.data.model.user.UserModel
import java.util.Date

data class StudentModel(
    override var ID: Int,
    var IdStudent: String,
    var Name: String,
    var PhoneNumber: String,
    var Email: String,
    var DateOfBirth: Date,
    var User: UserModel?,
    var Career: CareerModel?,
) : BaseModel(), java.io.Serializable
