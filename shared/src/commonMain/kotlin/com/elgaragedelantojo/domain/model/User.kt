package com.elgaragedelantojo.domain.model

import kotlinx.datetime.Instant

data class User(
    val id: String,
    val nombre: String,
    val telefono: String,
    val email: String,
    val direccion: Direccion,
    val role: UserRole = UserRole.CLIENTE,
    val createdAt: Instant,
    val ultimoPedido: String? = null
)
