package org.example

/**
 * Esta clase la cree para no repetir el codigo de la fun "contain" dentro de las Clases de Localizacion.
 * */

open class Locations {

    open var locations : MutableList<String> = mutableListOf()

    fun contain (location : String): Boolean {

        return locations.contains(location)
    }

}
