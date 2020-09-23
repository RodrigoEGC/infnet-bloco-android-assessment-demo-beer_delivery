package com.noplayer.assessmentdemobeerdelivery.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.ResultActivity
import com.noplayer.assessmentdemobeerdelivery.adapter.BeerCartRecyclerAdapter
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerCartViewModel
import kotlinx.android.synthetic.main.cart_beer_item_list.*
import kotlinx.android.synthetic.main.fragment_beer_cart.*
import java.lang.Exception

class BeerCartFragment : Fragment() {
    private lateinit var viewModelFactory : ViewModelFactory
    private lateinit var beerCartViewModel: BeerCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory()


        activity?.let {
            beerCartViewModel = ViewModelProvider(it, viewModelFactory).get(BeerCartViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_beer_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beerCartRecyclerView.adapter = BeerCartRecyclerAdapter(beerCartViewModel.getItems(), ::addCountItem, ::subCountItem)

        beerCartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeQuantity()

        observePrice()

        goCheckout()

        btn_back.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        addPriceTotal()

    }

    private fun observeQuantity() {
        try {
            beerCartViewModel.quantityAccumulator.observe(viewLifecycleOwner,
                { t -> beer_cart_quantity.text = t })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun observePrice() {
        try {
            beerCartViewModel.price.observe(viewLifecycleOwner,
                { t1 -> total_price.text = t1 })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun addCountItem(cartBeerItem: CartBeerItem) {
        beerCartViewModel.addCountItem(cartBeerItem)
        addPriceTotal()
    }

    private fun subCountItem(cartBeerItem: CartBeerItem) {
        beerCartViewModel.subCountItem(cartBeerItem)
        subPriceTotal()
    }


    @SuppressLint("SetTextI18n")
    private fun addPriceTotal() {
        val price = beerCartViewModel.getItems().fold(0.toDouble()) {accumulator, cartBeerItem -> accumulator + cartBeerItem.quantity.times(cartBeerItem.beerItem.price)  }

        total_price.text = "R$${price}"
    }

    @SuppressLint("SetTextI18n")
    private fun subPriceTotal() {
        val price = beerCartViewModel.getItems().fold(0.toDouble()) {accumulator, cartBeerItem -> accumulator - cartBeerItem.quantity.times(cartBeerItem.beerItem.price)  }

        total_price.text = "R$${price}"
    }

    private fun goCheckout() {
        btn_checkout.setOnClickListener {
            var beerName : String? = null
            var url : String? = null

            beerCartViewModel.getItems().forEach {
                beerName = it.beerItem.name
                url = it.beerItem.url
            }

            val intent = Intent(activity, ResultActivity::class.java)
            intent.putExtra("Name", beerName)
            intent.putExtra("Url", url)
            startActivity(intent)
        }
    }

}