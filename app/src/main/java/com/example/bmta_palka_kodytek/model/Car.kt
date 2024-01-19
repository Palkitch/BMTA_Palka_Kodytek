package com.example.bmta_palka_kodytek.model

import java.io.Serializable

class Car : Serializable {
    var brand: String = ""
    var model: String = ""
    var seats: Int = 0
    var consumption: Number = 0
    override fun toString(): String {
        return "Auto - '$brand', model: '$model', počet sedadel = $seats, spotřeba = '$consumption'L/100Km)"
    }


}
