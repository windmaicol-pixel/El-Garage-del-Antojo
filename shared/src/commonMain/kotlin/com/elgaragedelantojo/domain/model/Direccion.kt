package com.elgaragedelantojo.domain.model

data class Direccion(
    val condominio: String,
    val calle: String,
    val numeroExterior: String,
    val numeroInterior: String? = null,
    val codigoPostal: String = "76116",
    val colonia: String = "Puerta Navarra",
    val municipio: String = "Querétaro",
    val estado: String = "Querétaro",
    val referencias: String? = null
)
