package com.example.gestionacademicaapp.models

class Career : Base {
    var Code: String? = null
    var CareerName: String? = null
    var DegreeName: String? = null

    constructor() {}
    constructor(id: Int, code: String, careerName: String, degreeName: String){
        this.ID = id
        this.Code = code
        this.CareerName = careerName
        this.DegreeName = DegreeName
    }
}