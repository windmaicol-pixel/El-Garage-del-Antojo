package com.elgaragedelantojo.domain.model

data class Opcion(
    val nombre: String,
    val valores: List<String>,
    val precioExtra: Double = 0.0
)
