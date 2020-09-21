package com.noplayer.assessmentdemobeerdelivery.repository

import com.noplayer.assessmentdemobeerdelivery.model.BeerItem
import com.noplayer.assessmentdemobeerdelivery.R


class BeerData {
    fun allBeers(): List<BeerItem> {
        return listOf(
            BeerItem(
                "Trooper Tasting Kit",
                199.99,
                "Trooper Beer Tasting Kit With 5 Bottles.",
                R.drawable.kit_trooper,
                true,
            ),
            BeerItem(
                "Colorado Tasting Kit",
                99.99,
                "Colorado Beer Tasting Kit With 7 Bottles.",
                R.drawable.kit_colorado
            ),
            BeerItem(
                "Bamberg Tasting Kit",
                199.99, "Bamberg Beer Tasting Kit With 7 Bottles.",
                R.drawable.kit_bamberg
            ),
            BeerItem(
                "Mohave Tasting Kit", 199.99,
                "Mohave Beer Tasting Kit With 5 Bottles.",
                R.drawable.kit_mohave,
                true
            ),
            BeerItem(
                "Hocus Pocus Tasting Kit",
                199.99, "Leuven Beer Tasting Kit With 6 Bottles.",
                R.drawable.kit_hocus_pocus
            ),
            BeerItem(
                "Erdinger Tasting Kit",
                249.99, "Erdinger beer tasting kit with 6 bottles and 2 glasses.",
                R.drawable.kit_erdinger
            ),
            BeerItem(
                "Furst Tasting Kit", 179.99,
                "Furst Beer Tasting Kit With 11 Bottles and 1 glass.",
                R.drawable.kit_furst
            ),
            BeerItem(
                "American Tasting Kit",
                299.99, "American Beer Tasting Kit With 6 Bottles.",
                R.drawable.kit_california),
            BeerItem(
                "Wonderland Brewery Tasting Kit",
                159.99, "Wonderland Brewery Beer Tasting Kit With 6 Bottles.",
                R.drawable.kit_wonderland_brewery,
                true
            ),
        )
    }
}