package com.ecommerce.ecommerce.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.springframework.format.annotation.NumberFormat
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "PRODUCT")
class Product(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID? = null,

    @get: NotBlank(message = "It is Required")
    var name: String,

    @get: NotBlank(message = "It is Required")
    var sku: String,

    @get: NotBlank(message = "It is Required")
    var description: String,

    @get: DecimalMin(value="0.0", message = "The value must be greater than 0.0")
    @get: NumberFormat(style = NumberFormat.Style.NUMBER)
    var price: BigDecimal,

    val discount : Boolean

) : Serializable {
    init {
        if(discount) this.price = this.price.divide(BigDecimal(2))
    }
}