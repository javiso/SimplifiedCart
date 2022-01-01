package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.dto.CartAPI
import com.ecommerce.ecommerce.dto.ItemDto
import com.ecommerce.ecommerce.service.CartService
import ma.glasnost.orika.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/ecommerce/v1/cart")
class CartController(val cartService : CartService, val mapper : MapperFacade) {

    @PostMapping
    fun createProduct() = ResponseEntity(cartService.createCart(), HttpStatus.CREATED)

    @PutMapping("/{idCart}")
    fun updateCart(@PathVariable(name="idCart") idCart: UUID, @Valid @RequestBody item : ItemDto) : ResponseEntity<CartAPI> = ResponseEntity.ok(mapper.map(cartService.updateCart(idCart, item), CartAPI::class.java))

    @DeleteMapping("/{idCart}")
    fun deleteProductFromCart(@PathVariable(name="idCart") idCart: UUID, @RequestParam(value = "productId") productId : UUID) : Unit = cartService.deleteProductFromCart(idCart, productId)

    @GetMapping("/{idCart}")
    fun findbyId(@PathVariable(name="idCart", required = true) idCart: UUID): ResponseEntity<CartAPI> = ResponseEntity.ok(mapper.map(cartService.findById(idCart), CartAPI::class.java))

    @PutMapping("/checkout/{idCart}")
    fun checkout(@PathVariable(name="idCart") idCart: UUID) : BigDecimal = cartService.checkout(idCart)
}