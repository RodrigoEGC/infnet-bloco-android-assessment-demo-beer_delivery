package com.noplayer.assessmentdemobeerdelivery.model

data class BeerItem(
    val name: String,
    val price: Double,
    val description: String,
    val photo: Int,
    val isHighlyRated: Boolean,
    val url: String
)