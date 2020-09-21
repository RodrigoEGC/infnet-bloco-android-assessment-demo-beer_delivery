package com.noplayer.assessmentdemobeerdelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem
import kotlinx.android.synthetic.main.cart_beer_item_list.*
import kotlinx.android.synthetic.main.cart_beer_item_list.view.*


class BeerCartRecyclerAdapter(
    private val cartBeerItem: List<CartBeerItem>)
    : RecyclerView.Adapter<BeerCartRecyclerAdapter.BeerCartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerCartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_beer_item_list, parent, false)

        return BeerCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerCartViewHolder, position: Int) {
        val cart = cartBeerItem[position]

        holder.bindItem(cart)
    }

    override fun getItemCount() = cartBeerItem.size

    class BeerCartViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(cartBeerItem: CartBeerItem) {
            itemView.beerCartName.text = cartBeerItem.beerItem.name
            itemView.beerCartPrice.text = "R$${cartBeerItem.beerItem.price}"
            itemView.beerCartQuantity.text = cartBeerItem.quantity.toString()
            Glide.with(view).load(cartBeerItem.beerItem.photo).into(itemView.beerCartPhoto)

            itemView.btnAddCount.setOnClickListener {
                var quantity = 0
                itemView.beerCartQuantity.text = (quantity + 1).toString()
            }

            itemView.btnSubCount.setOnClickListener {
                val item = cartBeerItem.quantity
                item - 1
            }
        }
    }


}