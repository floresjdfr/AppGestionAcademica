package com.example.gestionacademicaapp.data.model.user

import com.example.gestionacademicaapp.data.model.BaseModel

class UserModel(override var ID: Int, var UserID: String, var Password: String, var UserType: UserType) : BaseModel()
