package com.ecommerce.ecommerce.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ApiError(
    val status: HttpStatus,
    val message: String,
    var errors: List<String?> = mutableListOf(),
    val times: LocalDateTime = LocalDateTime.now()
)