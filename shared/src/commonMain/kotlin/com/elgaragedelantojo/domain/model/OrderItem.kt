package com.elgaragedelantojo.domain.model

data class OrderItem(
    val producto: Product,
    val cantidad: Int,
    val opcionesSeleccionadas: Map<String, String>? = null,
    val precioUnitario: Double,
    val precioTotal: Double
)
