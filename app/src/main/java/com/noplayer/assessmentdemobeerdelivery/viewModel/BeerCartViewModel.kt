package com.noplayer.assessmentdemobeerdelivery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem

class BeerCartViewModel: ViewModel() {

    var saveItems = mutableListOf<CartBeerItem>()

    var quantityAccumulator = MutableLiveData<String>()

    fun addItem(cartBeerItem: CartBeerItem) {
       saveItems.add(cartBeerItem)
    }

    fun getItems(): MutableList<CartBeerItem> {
        return saveItems
    }


    fun addCountItem(qtde: Int) {
        qtde + 1
    }

    fun subCountItem(qtde: Int) {
        qtde - 1
    }


}