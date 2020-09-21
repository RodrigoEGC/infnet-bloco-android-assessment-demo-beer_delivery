package com.noplayer.assessmentdemobeerdelivery.model

data class BeerItem(
    var name: String,
    val price: Double,
    val description: String,
    val photo: Int,
    val isHighlyRated: Boolean = false
)