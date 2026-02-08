package com.elgaragedelantojo.domain.model

import kotlinx.datetime.Instant

data class Order(
    val id: String,
    val folio: String,
    val userId: String,
    val userName: String,
    val telefono: String,
    val direccion: Direccion,
    val items: List<OrderItem>,
    val total: Double,
    val metodoPago: MetodoPago,
    val detallesPago: DetallesPago? = null,
    val status: OrderStatus,
    val origen: OrigenPedido,
    val creadoPor: String? = null,
    val createdAt: Instant,
    val confirmedAt: Instant? = null,
    val deliveredAt: Instant? = null,
    val notas: String? = null
)
