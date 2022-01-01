package com.ecommerce.ecommerce.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ITEM")
class Item (product : Product, cart: Cart, amount : Int) : Serializable{

    @EmbeddedId
    val pk : ItemPK

    @NotNull
    var amount : Int = 0

    init {
        pk = ItemPK(cart, product)
        this.amount = amount
    }
}