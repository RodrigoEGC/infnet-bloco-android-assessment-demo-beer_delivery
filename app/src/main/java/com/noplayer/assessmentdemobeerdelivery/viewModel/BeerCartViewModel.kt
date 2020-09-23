package com.noplayer.assessmentdemobeerdelivery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem

class BeerCartViewModel: ViewModel() {

    var saveItems = mutableListOf<CartBeerItem>()

    var quantityAccumulator = MutableLiveData<String>()

    var price = MutableLiveData<String>()

    fun addItem(cartBeerItem: CartBeerItem) {
       saveItems.add(cartBeerItem)
    }

    fun getItems(): MutableList<CartBeerItem> {
        return saveItems
    }

    fun addCountItem(cartBeerItem: CartBeerItem) {
        val index = saveItems.indexOf(cartBeerItem)
        saveItems[index].quantity = (saveItems[index].quantity + 1)
    }

    fun subCountItem(cartBeerItem: CartBeerItem) {
        val index = saveItems.indexOf(cartBeerItem)
        saveItems[index].quantity = (saveItems[index].quantity - 1)
        if (saveItems[index].quantity == 0) {
            removeCart(cartBeerItem)
        }
    }

    private fun removeCart(cartBeerItem: CartBeerItem) {
        val index = saveItems.indexOf(cartBeerItem)
        saveItems.removeAt(index)
    }

}