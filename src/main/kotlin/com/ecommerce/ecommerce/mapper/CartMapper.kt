package com.ecommerce.ecommerce.mapper

import com.ecommerce.ecommerce.dto.CartAPI
import com.ecommerce.ecommerce.dto.ItemDto
import com.ecommerce.ecommerce.model.Cart
import com.ecommerce.ecommerce.model.Item
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer
import ma.glasnost.orika.MapperFactory
import org.springframework.stereotype.Component

@Component
class CartMapper: OrikaMapperFactoryConfigurer {

    override fun configure(orikaMapperFactory: MapperFactory) {

        orikaMapperFactory.classMap(Cart::class.java, CartAPI::class.java)
            .byDefault()
            .register()
        orikaMapperFactory.classMap(Item::class.java, ItemDto::class.java)
            .field("pk.product", "product")
            .byDefault()
            .mapNulls(false)
            .register()
    }
}