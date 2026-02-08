package com.elgaragedelantojo.domain.model

data class DetallesPago(
    val necesitaCambio: Boolean = false,
    val pagaraCon: Double? = null,
    val cambio: Double? = null,
    val comprobanteUrl: String? = null
)
