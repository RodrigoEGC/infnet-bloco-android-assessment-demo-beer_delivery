package com.noplayer.assessmentdemobeerdelivery.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerCartViewModel
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerListViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BeerListViewModel::class.java) -> {
                BeerListViewModel() as T
            }
            modelClass.isAssignableFrom(BeerCartViewModel::class.java) -> {
                BeerCartViewModel() as T
            }
            else -> throw IllegalArgumentException("Class View Model Unknown")
        }
    }

}