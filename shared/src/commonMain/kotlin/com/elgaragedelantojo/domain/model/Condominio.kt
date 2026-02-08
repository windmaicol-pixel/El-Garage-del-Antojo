package com.elgaragedelantojo.domain.model

data class Condominio(
    val id: String,
    val nombre: String,
    val calle: String,
    val colonia: String = "Puerta Navarra",
    val cp: String = "76116",
    val activo: Boolean = true,
    val referencias: String? = null
)
