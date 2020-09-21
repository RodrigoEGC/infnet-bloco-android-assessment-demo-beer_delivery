package com.noplayer.assessmentdemobeerdelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.adapter.BeerCartRecyclerAdapter
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
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

        beerCartRecyclerView.adapter = BeerCartRecyclerAdapter(beerCartViewModel.getItems())

        beerCartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeQuantity()

        val totalPriceAcc = beerCartViewModel.getItems()
            .fold(0.toDouble()) {accumulator, carBeerItem -> accumulator + carBeerItem.quantity.times(carBeerItem.beerItem.price!!.toDouble())}

        totalPrice.text = "R$${totalPriceAcc}"
    }

    private fun observeQuantity() {
        try {
            beerCartViewModel.quantityAccumulator.observe(viewLifecycleOwner,
                { t -> beerCartQuantity.text = t })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }



}