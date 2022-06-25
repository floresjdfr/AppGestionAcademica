package com.example.gestionacademicaapp.data.model

import com.example.gestionacademicaapp.data.model.user.UserModel

data class TeacherModel(override var ID: Int, var IdIdentidad: String, var Name: String, var PhoneNumber: String, var Email: String, var User: UserModel?): BaseModel(), java.io.Serializable
