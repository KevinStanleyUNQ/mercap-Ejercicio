package org.example



/**
 * Reemplace las Localidades Nacional por Provincias dado que eran demasiadas y no quedaba como buena practica una MutableList con tantos Strings.
 * */

class NationalsLocations : Locations() {

    override var locations : MutableList<String> = mutableListOf("Catamarca", "Chaco", "Chubut", "Cordoba", "Corrientes",
        "Entre Rios", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquen", "Río Negro", "Salta",
        "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero", "Tierra del Fuego")

}



