package com.ecommerce.ecommerce.repository

import com.ecommerce.ecommerce.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository : JpaRepository<Product, UUID>{
}