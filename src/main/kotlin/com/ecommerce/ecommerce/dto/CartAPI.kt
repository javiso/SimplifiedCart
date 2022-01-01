package com.ecommerce.ecommerce.dto

import com.ecommerce.ecommerce.enumerator.CartState
import java.util.*

data class CartAPI (
    val id : UUID,
    val state: CartState,
    val items : MutableSet<ItemDto>
    )