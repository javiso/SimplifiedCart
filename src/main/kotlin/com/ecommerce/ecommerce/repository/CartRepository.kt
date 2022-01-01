package com.ecommerce.ecommerce.repository

import com.ecommerce.ecommerce.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartRepository : JpaRepository<Cart, UUID>{

}