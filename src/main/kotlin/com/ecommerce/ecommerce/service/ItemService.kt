package com.ecommerce.ecommerce.service

import com.ecommerce.ecommerce.model.Item
import com.ecommerce.ecommerce.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ItemService(val itemRepository: ItemRepository) {

    fun createItem(item : Item) = itemRepository.save(item)

    fun deleteItem(item : Item) = itemRepository.delete(item)
}