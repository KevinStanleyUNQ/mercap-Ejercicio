package org.example

class Billing(user : User){

    var billigUser = user
    var localCallPay = 0.0
    var natCallPay = 0.0
    var interCallPay = 0.0
    val monthlyPayment : Int = 1500


    fun totalMount(): Double {

        var totalPay = monthlyPayment + localCallPay + natCallPay + interCallPay

        println("Factura de: ${billigUser.name}" + '\n')
        println("Costo por Llamadas Locales: $localCallPay")
        println("Costo por Llamadas Nacionales: $natCallPay")
        println("Costo por Llamadas Internacionales: $interCallPay")
        println("Costo fijo Mensual: $monthlyPayment")
        println("--------------------------------------------------")
        println("Total: $totalPay")
        println("**************************************************")

        return totalPay
    }

}

