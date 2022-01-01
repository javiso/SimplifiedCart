package com.ecommerce.ecommerce.repository

import com.ecommerce.ecommerce.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemRepository  : JpaRepository<Item, UUID>{}