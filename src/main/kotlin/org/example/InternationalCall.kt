package org.example

import java.time.LocalDateTime

class InternationalCall(var location: String, override var initCall: LocalDateTime, override var finalCall: LocalDateTime) : TypeCall() {

    /** No contemplo dias habiles ni franja horaria, solo tomo el valor que tiene llamar a ese pais. */

    override fun calculatePay(): Double {

        var minutes = difBetweenDateTime()
        var value = 0.0

         when(location){
            "Estados Unidos" -> value += 50.0 * minutes
            "Francia" -> value += 60.0 * minutes
            "Italia" -> value += 70.0 * minutes
            "Japon" -> value += 80.0 * minutes
            "China" -> value += 90.0 * minutes
            "Korea" ->value += 100.0 * minutes
            "Chile" -> value += 110.0 * minutes
            "Bolivia" -> value += 120.0 * minutes
            "Colombia" -> value += 130.0 * minutes
            "Venenzuela" -> value += 140.0 * minutes
            "Brasil" -> value += 150.0 * minutes
            "Uruguay" -> value += 160.0 * minutes
            "Paraguay" -> value += 170.0 * minutes
            else -> value = 0.0
        }
        return  value
    }

    override fun loadPay(user: User) {
        user.internationalCallPay += calculatePay()
    }
}
