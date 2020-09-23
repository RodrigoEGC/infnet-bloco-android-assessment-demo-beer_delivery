package com.noplayer.assessmentdemobeerdelivery.viewModel

import androidx.lifecycle.ViewModel
import com.noplayer.assessmentdemobeerdelivery.model.BeerItem
import com.noplayer.assessmentdemobeerdelivery.repository.BeerData

class BeerListViewModel: ViewModel() {

    var getBeerItem: BeerItem? = null

    fun all(): List<BeerItem> {
        return BeerData().allBeers()
    }

}