package org.example

import java.time.LocalDateTime

class User(var name: String) {

    var localCallPay = 0.0
    var nationalCallPay = 0.0
    var internationalCallPay = 0.0
    lateinit var actualCall : Call
    var firstCall : LocalDateTime? = null
    var initLastCall : LocalDateTime? = null
    lateinit var lastCall : Call

    fun call(location: String, initCall: LocalDateTime, finalCall: LocalDateTime){
        ifIsFirtCallOfMonth(initCall)
        actualCall = Call(location,initCall, finalCall, this)
        verifyCallInProgress()
    }

    private fun ifIsFirtCallOfMonth(initCall: LocalDateTime) {
        if (firstCall == null) {
            firstCall = initCall
        }
    }
    private fun verifyCallInProgress() {


        if(initLastCall == null || initLastCall!!.isBefore(actualCall.initCall) ){
            actualCall.typeCall.loadPay(this)
            initLastCall = actualCall.initCall
            lastCall = actualCall
        }
        else {
            var exception = Exception("La llamada hacia: ${lastCall.location} esta en curso")
            println("**************************************************")
            println("${exception.message}")
            println("**************************************************")
            throw exception

        }
    }
    fun getBilling(): Double {

        var billing = Billing(this)
        if(initLastCall!! > firstCall!!.plusMonths(1)){

            billing = actualCall.generateBillig()
            billing.localCallPay = localCallPay
            billing.interCallPay = internationalCallPay
            billing.natCallPay = nationalCallPay
            chargeNewPayValueUser()
        }
        return billing.totalMount()

    }

    private fun chargeNewPayValueUser() {

        localCallPay = 0.0
        nationalCallPay = 0.0
        internationalCallPay = 0.0
    }

}
