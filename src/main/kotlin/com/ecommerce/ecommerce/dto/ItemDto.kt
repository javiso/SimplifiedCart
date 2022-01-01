package com.ecommerce.ecommerce.dto

import com.ecommerce.ecommerce.model.Product
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class ItemDto(
    val product : Product,

    @get: NotNull(message = "This field cannot be null")
    @get: Min(value = 0, message = "This field must be greater than 0")
    val amount: Int )
