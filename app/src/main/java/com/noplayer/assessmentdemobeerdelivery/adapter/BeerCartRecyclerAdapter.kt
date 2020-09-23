package com.noplayer.assessmentdemobeerdelivery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem
import kotlinx.android.synthetic.main.cart_beer_item_list.view.*


class BeerCartRecyclerAdapter (
    private val cartBeerItem: List<CartBeerItem>,
    private val addItem: (cartBeerItem: CartBeerItem) -> Unit,
    private val removeItem: (cartBeerItem: CartBeerItem) -> Unit
)
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

    inner class BeerCartViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(cartBeerItem: CartBeerItem) {
            itemView.beer_cart_name.text = cartBeerItem.beerItem.name
            itemView.beer_cart_price.text = "R$${cartBeerItem.beerItem.price}"
            itemView.beer_cart_quantity.text = cartBeerItem.quantity.toString()
            Glide.with(view).load(cartBeerItem.beerItem.photo).into(itemView.beer_cart_photo)

            itemView.btn_add_count.setOnClickListener {
                cartBeerItem.quantity = cartBeerItem.quantity++
                itemView.beer_cart_quantity.text = cartBeerItem.quantity.toString()
                notifyDataSetChanged()
                addItem(cartBeerItem)
            }

            itemView.btn_sub_count.setOnClickListener {
                cartBeerItem.quantity = cartBeerItem.quantity--
                itemView.beer_cart_quantity.text = cartBeerItem.quantity.toString()
                notifyDataSetChanged()
                removeItem(cartBeerItem)
            }
        }
    }
}