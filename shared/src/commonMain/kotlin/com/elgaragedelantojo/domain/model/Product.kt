package com.elgaragedelantojo.domain.model

data class Product(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: Categoria,
    val imageUrl: String,
    val disponible: Boolean = true,
    val opciones: List<Opcion>? = null
)
