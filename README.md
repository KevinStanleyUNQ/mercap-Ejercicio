# mercap-Ejercicio
## Aclaraciones:

El ejercicio tiene comentarios en los Test para saber en que contexto se esta trabajando, intente contemplar todos los casos posibles como por ej:

* No se puede llamar a dos lugares distintos a la vez, eso lanza una Excepcion.
* Si se quiere llamar un lugar que no pertenece a ninguna lista de las localizaciones locales, nacionales e internacionales, tambien se lanza una Excepcion.

Dada mi interpretacion del enunciado hice unas modificaciones para que no sea tan extenso el codigo, que no rompen con la idea del enunciado sino que utilizo menos String, 
por ej:

* Cambie las Localidades de Provincias por Provincias para que fuera un poco mas reducido dado que sino seria muy extenso y no tome en cuenta los dias habiles ni una franja de horario como las llamadas locales que de 8am a 20pm tenian un costo. Solo les asigne un valor fijo sin importar en que horario llamaran.

* Reduje la cantidad de Paises a los que se puede llamar, por la misma razon que el anterior, ingrese una cantidad limitada de paises y si bien en los test donde simulo la Excepcion la localizacion a la que se llama existe en el mundo real, solo lanza dicha Excepcion porque no esta incluido en la lista.

* Handle las Excepciones con un Print para que se vea que es lo que ocurrio.

* Solo se genera una Factura por mes cuando la llamada a realizar ocurre un mes despues de su primera llamada en el mes. Es decir :

    ```
    primeraLlamadaDelMes = 15/02/2021
    nuevaLlamadaDelMesProximo = 17/03/2021
    
    Se genera Facturacion.
    ```

* Tome el caso tambien de que si no realizo una llamada un mes despues, pero si dentro del mes de su primera llamada del mes, al pedirle una Facturacion solo me da una Factura
con el valor fijo y todos los demas valores en 0, pero sin perder el costo de dichas llamadas, y solo se genera la Factura completa al llamar un mes despues, 
porque si le doy una Factura con todos los valores cargados estoy dandole la oportunidad de pagar antes que se cumpla el mes.
