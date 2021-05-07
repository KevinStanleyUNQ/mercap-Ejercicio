package org.example

import java.time.LocalDateTime

abstract class TypeCall {

    abstract var initCall : LocalDateTime
    abstract var finalCall : LocalDateTime
    //abstract var payMount : Double


    abstract fun calculatePay() : Double

    abstract fun loadPay(user: User)

    fun generateBillig(user: User): Billing{
        return Billing(user)
    }


    fun difBetweenDateTime(): Int {

        var callInitInMinute = toMinute(initCall)
        var callFinalInMinute = toMinute(finalCall)

        return  callFinalInMinute- callInitInMinute
    }

    fun toMinute(aDate: LocalDateTime): Int {
        return aDate.hour * 60 + aDate.minute
    }
}
