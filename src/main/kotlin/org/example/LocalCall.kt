package org.example

import java.time.LocalDateTime

class LocalCall(var location: String, override var initCall: LocalDateTime, override var finalCall: LocalDateTime) : TypeCall() {

    override fun calculatePay(): Double {
        var value = 0.0
        val businessDay = listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY")
        var day  = initCall.dayOfWeek.toString()

        if(!businessDay.contains(day)){
                value += 0.1 * difBetweenDateTime()
            }
        else{
            if(initCall.hour in 8..20){
                value += 0.2 * difBetweenDateTime()
            }
            else {
                value += 0.1 * difBetweenDateTime()
            }
        }
        return value
    }

    override fun loadPay(user: User) {
        user.localCallPay += calculatePay()
    }

}
