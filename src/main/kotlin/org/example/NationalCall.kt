package org.example

import java.time.LocalDateTime

class NationalCall(var location: String, override var initCall: LocalDateTime, override var finalCall: LocalDateTime) : TypeCall(){

    override fun calculatePay(): Double {

        var minutes = difBetweenDateTime()
        var value = 0.0

        when(location){
            "Catamarca" -> value += 1.0 * minutes
            "Chaco" -> value += 2.0 * minutes
            "Chubut" -> value += 3.0 * minutes
            "Cordoba" -> value += 4.0 * minutes
            "Corrientes" -> value += 5.0 * minutes
            "Entre Rios" ->value += 6.0 * minutes
            "Formosa" -> value += 7.0 * minutes
            "Jujuy" -> value += 8.0 * minutes
            "La Pampa" -> value += 9.0 * minutes
            "La Rioja" -> value += 10.0 * minutes
            "Mendoza" -> value += 11.0 * minutes
            "Misiones" -> value += 12.0 * minutes
            "Neuquen" -> value += 13.0 * minutes
            "Río Negro" -> value += 14.0 * minutes
            "Salta" -> value += 15.0 * minutes
            "San Juan" -> value += 16.0 * minutes
            "San Luis" -> value += 17.0 * minutes
            "Santa Cruz" -> value += 18.0 * minutes
            "Santa Fe" -> value += 19.0 * minutes
            "Santiago del Estero" -> value += 20.0 * minutes
            "Tierra del Fuego" -> value += 21.0 * minutes
            else -> value = 0.0
        }
        return  value

    }

    override fun loadPay(user: User) {
        user.nationalCallPay += calculatePay()
    }
}
