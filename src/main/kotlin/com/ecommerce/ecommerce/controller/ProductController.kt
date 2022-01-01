package com.ecommerce.ecommerce.controller

import com.ecommerce.ecommerce.model.Product
import com.ecommerce.ecommerce.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/ecommerce/v1/product")
class ProductController(val productService : ProductService) {

    @GetMapping("/all")
    fun findAll() : ResponseEntity<List<Product>> = ResponseEntity.ok(productService.findAll())

    @GetMapping("/{idProduct}")
    fun findById(@PathVariable(name = "idProduct") idProduct: UUID) : ResponseEntity<Product> = ResponseEntity.ok(productService.findById(idProduct))

    @PostMapping
    fun createProduct( @Valid @RequestBody product: Product) = ResponseEntity(productService.createProduct(product), HttpStatus.CREATED)

    @PutMapping("/{idProduct}")
    fun updateProduct(@PathVariable(name="idProduct") idProduct: UUID, @RequestBody product : Product) : ResponseEntity<Product> =
        productService.updateProduct(idProduct, product).let { ResponseEntity.ok(it) }

    @DeleteMapping("/{idProduct}")
    fun deleteProduct(@PathVariable(name="idProduct") idProduct: UUID) : Unit = productService.deleteProduct(idProduct)
}