package com.ecommerce.ecommerce.model

import com.ecommerce.ecommerce.enumerator.CartState
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CART")
class Cart (

    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2", strategy="uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type="uuid-char")
    val id: UUID?= null,

    @OneToMany(mappedBy = "pk.cart")
    val items : MutableSet<Item> = mutableSetOf(),

    @Enumerated(EnumType.STRING)
    var state : CartState = CartState.PENDING,
) : Serializable