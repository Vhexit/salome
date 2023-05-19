package com.example.carmanager

class Cars {


    var carmake:String = ""
    var carmodel:String = ""
    var carprice:String = ""

    var car_id:String = ""

    constructor(carmake: String, carmodel: String, carprice: String, car_id: String) {
        this.carmake = carmake
        this.carmodel = carmodel
        this.carprice = carprice
        this.car_id = car_id
    }

    constructor()
}