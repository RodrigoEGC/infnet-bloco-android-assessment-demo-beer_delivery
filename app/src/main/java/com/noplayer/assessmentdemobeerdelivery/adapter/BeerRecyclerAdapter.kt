package com.noplayer.assessmentdemobeerdelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.model.BeerItem
import kotlinx.android.synthetic.main.row_beer_delivery.view.*

class BeerRecyclerAdapter(
    private val beerItems: List<BeerItem>,
    private var eventClick: (BeerItem) -> Unit)
    : RecyclerView.Adapter<BeerRecyclerAdapter.BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_beer_delivery, parent, false)

        val beerViewHolder = BeerViewHolder(view)

        beerViewHolder.itemView.setOnClickListener{
            val beer = beerItems[beerViewHolder.adapterPosition]
            eventClick(beer)
        }
        return beerViewHolder
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beerItem = beerItems[position]
        holder.bind(beerItem)
    }

    override fun getItemCount(): Int = beerItems.size

    class BeerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(beerItem: BeerItem) {
            itemView.beerItemTitle.text = beerItem.name
            itemView.beerPrice.text = "Price: R$ ${beerItem.price}"
            Glide.with(view).load(beerItem.photo).into(itemView.mainPhoto)
        }
    }
}