package org.example

import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BillingTest {

    var user1 = User("User1")

    var dateInit_2021_5_5 = LocalDate.of(2021,5,5)
    var dateFinal_2021_5_5 = LocalDate.of(2021,5,5)
    var timeInit_15_46_25 = LocalTime.of(15,46,25)
    var timeFinal_15_59_12 = LocalTime.of(15,59,12)
    var dateInit_2021_6_6 = LocalDate.of(2021,6,6)
    var dateFinal_2021_6_6 = LocalDate.of(2021,6,6)
    var timeInit_5_12_27 = LocalTime.of(5,12,27)
    var timeFinal_5_15_58 = LocalTime.of(5,15,58)
    var dateInit_2021_5_15 = LocalDate.of(2021,5,15)
    var dateFinal_2021_5_15 = LocalDate.of(2021,5,15)
    var timeInit_12_48_10 = LocalTime.of(12,48,10)
    var timeFinal_13_5_19 = LocalTime.of(13,5,19)


    /** Test para dias Habiles dentro del horario de 8am a 20pm y con Facturacion Mensual, con una llamada
     * dentro del horario de 8am a 20pm y con otra llamada fuera de ese rango. */
    @Test
    fun testBillingLocalBetween8And20HoursInBusinessDayWithMensualPay(){

        user1.call("Quilmes", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call(("Avellaneda"), LocalDateTime.of(dateInit_2021_6_6,timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))
        assertEquals(1502.9, user1.getBilling())
    }

    /** Test con llamada dentro del rango de 8am a 20pm en un dia habil, sin Facturacion Mensual. */
    @Test
    fun testBillingLocalBetween8And20HoursInBusinessDayWithoutMensualPay(){

        user1.call("Quilmes", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        assertEquals(1500.0, user1.getBilling())

    }

    /** Test con llamada en dia no habil fuera del rango de 8am a 20pm al inciar, pero dentro de
     * ese rango al finalizar sin Factura Mensual.
     * Solo contemplo el costo por minuto de  cuando se inicio la llamada y no los minutos fuera del rango mas los
     * minutos dentro del rango. */

    @Test
    fun testBillingLocaInNoBusinessDayWithoutMensualPay(){

        user1.call("Ezeiza", LocalDateTime.of(dateInit_2021_6_6, timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))
        assertEquals(1500.0, user1.getBilling())
    }

    /** Test con dos llamadas una dentro del rango de 8am a 20pm en un dia habil y otra fuera de ese
     * rango pero en dia no habil, con Factura Mensual. */

    @Test
    fun testBillingLocalCallInNoBusinessDayAndBusinessDayWithMensualPay(){

        user1.call("Quilmes", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call(("Avellaneda"), LocalDateTime.of(dateInit_2021_6_6, timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))

        assertEquals(1502.9, user1.getBilling())
    }

    /** Test cuando se intenta llamar a un lugar desconocido, se lanza una Excepcion y checkeo que asi sea. */
    @Test
    fun testBillingLocalLocationUnknown() {

        assertFailsWith(
            Exception::class,
            { user1.call("Irak", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
              user1.getBilling()}
        )
    }

    /** Test con 2 llamadas Internacionales y con Factura Mensual. */

    @Test
    fun testInternationalCallWithMensualPay(){


        user1.call("Estados Unidos", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Chile", LocalDateTime.of(dateInit_2021_6_6, timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))

        assertEquals(2480.0, user1.getBilling())

    }

    /** Test con 2 llamadas Internacionales y una Local con Factura Mensual en orden cronologico, dado que si lo hago
     * en un orden no cronologico falla y tiene sentido, debido a que no puedo llamar un Viernes y luego realizar una
     * llamada antes de ese dia. */
    @Test
    fun testTwoInternationalCallAndOneLocalCallWithMensualPay(){
        user1.call("Estados Unidos", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Ezeiza", LocalDateTime.of(dateInit_2021_5_15, timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_5_15, timeFinal_5_15_58))
        user1.call("Chile", LocalDateTime.of(dateInit_2021_6_6,timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))

        assertEquals(2480.3, user1.getBilling())

    }

    /** Test para comprobar que lanza una Excepcion cuando se intentan realizar dos llamadas a la vez */

    @Test
    fun testLocalAndInternationalCallSameTimeWithMensualPay(){
        assertFailsWith(Exception::class,{
        user1.call("Estados Unidos", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Ezeiza", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Chile", LocalDateTime.of(dateInit_2021_6_6,timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))

        user1.getBilling()})
    }

    /** Test que retorna el valor de la Facturacion sin pago Mensual. */

    @Test
    fun testNationalCallWithoutMensualPay(){

        user1.call("Neuquen", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))

        assertEquals(1500.0, user1.getBilling())
    }

    /** Test de llamada Nacional con Factura Mensual. */

    @Test
    fun testNationalCallWithMensualPay(){

        user1.call("Cordoba", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Santiago del Estero", LocalDateTime.of(dateInit_2021_6_6, timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_6_6, timeFinal_5_15_58))

        assertEquals(1612.0, user1.getBilling())
    }

    /** Test con llamada Nacional, Internacional y Local sin Factura Mensual realizada. */

    @Test
    fun testNationalAndInternationalAndLocalCallWithoutMensualPay(){

        user1.call("Quilmes", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Estados Unidos", LocalDateTime.of(dateInit_2021_5_15,  timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_5_15, timeFinal_5_15_58))
        user1.call("Santiago del Estero", LocalDateTime.of(dateInit_2021_5_15, timeInit_12_48_10), LocalDateTime.of(dateInit_2021_5_15, timeFinal_13_5_19))

        assertEquals(1500.0, user1.getBilling())
    }

    /** Test llamada Nacional, Internacional y Local con Factura Mensual. */

    @Test
    fun testNationalAndInternationalAndLocalCallWithMensualPay(){

        user1.call("Quilmes", LocalDateTime.of(dateInit_2021_5_5, timeInit_15_46_25), LocalDateTime.of(dateFinal_2021_5_5, timeFinal_15_59_12))
        user1.call("Estados Unidos", LocalDateTime.of(dateInit_2021_5_15,  timeInit_5_12_27), LocalDateTime.of(dateFinal_2021_5_15, timeFinal_5_15_58))
        user1.call("Santiago del Estero", LocalDateTime.of(dateInit_2021_6_6, timeInit_12_48_10), LocalDateTime.of(dateInit_2021_6_6, timeFinal_13_5_19))

        assertEquals(1992.6, user1.getBilling())

    }
}
