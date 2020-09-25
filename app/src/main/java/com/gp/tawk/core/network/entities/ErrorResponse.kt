package com.gp.tawk.core.network.entities

data class ErrorResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val error: String
)