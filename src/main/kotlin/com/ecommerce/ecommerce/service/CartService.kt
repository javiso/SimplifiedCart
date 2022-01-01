package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.dto.ItemDto
import com.ecommerce.ecommerce.enumerator.CartState
import com.ecommerce.ecommerce.exception.CartNotFound
import com.ecommerce.ecommerce.model.Cart
import com.ecommerce.ecommerce.model.Item
import com.ecommerce.ecommerce.repository.CartRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Service
@Transactional
class CartService(
    val cartRepository: CartRepository,
    val itemService: ItemService,
    val productService: ProductService
) {

    fun createCart(): UUID? = cartRepository.save(Cart()).id

    fun updateCart(idCart: UUID, itemDto: ItemDto): Cart =
        this.findById(idCart).let { cart ->
            cart.items.firstOrNull { item -> item.pk.product.id?.equals(itemDto.product.id) == true }?.let { itemCart ->
                modifyAmountProduct(cart, itemDto, itemCart)
            } ?: addNewProductToCart(itemDto, cart)
        }

    private fun addNewProductToCart(itemDto: ItemDto, cart: Cart): Cart {
        val product = itemDto.product.id?.let { productService.findById(it) }
        cart.items.add(itemService.createItem(Item(product!!, cart, itemDto.amount)))
        return cartRepository.save(cart)
    }

    private fun modifyAmountProduct(cart: Cart, itemDto: ItemDto, itemCart: Item): Cart {
        itemCart.amount = itemDto.amount
        return cartRepository.save(cart)
    }

    fun deleteProductFromCart(idCart: UUID, productId: UUID) {
        this.findById(idCart).let { cart ->
            cart.items.firstOrNull { item -> item.pk.product.id?.equals(productId) == true }?.let { itemCart ->
                itemService.deleteItem(itemCart)
                cartRepository.save(cart)
            }
        }
    }

    fun findById(idCart: UUID): Cart = cartRepository.findByIdOrNull(idCart) ?: throw CartNotFound("Cart not found")

    fun checkout(idCart: UUID): BigDecimal {
        val cart = this.findById(idCart)
        cart.state = CartState.COMPLETED
        cartRepository.save(cart)
        return this.findById(idCart).items.sumOf { item -> item.pk.product.price.multiply(BigDecimal(item.amount)) }
    }
}