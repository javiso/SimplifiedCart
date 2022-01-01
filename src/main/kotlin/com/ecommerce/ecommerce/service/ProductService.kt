package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.exception.ProductNotFound
import com.ecommerce.ecommerce.model.Product
import com.ecommerce.ecommerce.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class ProductService(val productRepository : ProductRepository) {

    fun findAll() : List<Product> = productRepository.findAll()

    fun createProduct(product : Product) : Product = productRepository.save(product)

    fun findById(id : UUID) : Product = productRepository.findByIdOrNull(id) ?: throw ProductNotFound("Product not found")

    fun updateProduct(idProduct: UUID, productUpdated: Product) : Product =

        this.findById(idProduct).let { product->
            product.name = productUpdated.name
            product.price = if (productUpdated.discount) productUpdated.price.divide(BigDecimal(2)) else productUpdated.price
            product.sku = productUpdated.sku
            product.description = productUpdated.description
            product.discount = productUpdated.discount

            return productRepository.save(product)
        }

    fun deleteProduct(idProduct: UUID) {
        productRepository.deleteById(idProduct)
    }
}