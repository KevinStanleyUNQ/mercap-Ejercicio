package org.example

import java.time.LocalDateTime

class Call(var location: String, override var initCall: LocalDateTime, override var finalCall: LocalDateTime, var user: User) : TypeCall(){

    var typeCall = checkTypeCall(location, initCall, finalCall)

    fun generateBillig(): Billing {
        return typeCall.generateBillig(user)
    }

    override fun calculatePay(): Double {
        return 0.0
    }

    override fun loadPay(user: User) {}

    private fun checkTypeCall(location: String, initCall: LocalDateTime, finalCall: LocalDateTime): TypeCall {

        lateinit var correspondingCall : TypeCall
        var locationsLocal = LocalsLocations()
        var locationsNational = NationalsLocations()
        var locationsInternational = InternationalLocations()

        if(locationsLocal.contain(location)){
            correspondingCall = LocalCall(location, initCall, finalCall)
        }
        else if (locationsNational.contain(location)){
            correspondingCall = NationalCall(location, initCall, finalCall)
        }
        else if (locationsInternational.contain(location)){
            correspondingCall = InternationalCall(location, initCall, finalCall)
        }
        else {

            var exception = Exception("La localizacion no existe")
            println("**************************************************")
            println("${exception.message}")
            println("**************************************************")
            throw exception
        }

        return correspondingCall
    }

}
