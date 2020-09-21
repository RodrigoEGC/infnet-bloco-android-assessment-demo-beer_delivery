package com.noplayer.assessmentdemobeerdelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerCartViewModel
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerItemViewModel
import kotlinx.android.synthetic.main.fragment_beer_info.*
import java.lang.Exception

class BeerInfoFragment : Fragment() {
    private lateinit var viewModelFactory : ViewModelFactory
    private lateinit var beerItemViewModel : BeerItemViewModel
    private lateinit var beerCartViewModel: BeerCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory()

        activity?.let {
            beerItemViewModel = ViewModelProvider(it, viewModelFactory).get(BeerItemViewModel::class.java)
            beerCartViewModel = ViewModelProvider(it, viewModelFactory).get(BeerCartViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_beer_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBeerDetails()
        buyItem()
        addItemCart()
    }


    private fun setBeerDetails() {
        try {
            val beerItem = beerItemViewModel.getBeerItem!!

            if (beerItem.isHighlyRated) {
                highlyRatedIcon.visibility = View.VISIBLE
            }

            beerInfoName.text = beerItem.name
            beerInfoDescription.text = beerItem.description
            beerInfoPrice.text = beerItem.price.toString()
            beerInfoPhoto.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    beerItem.photo
                )
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Unknown beer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buyItem() {
        buy.setOnClickListener {
            findNavController().navigate(R.id.action_beerInfoFragment_to_resultActivity)
        }
    }

    private fun addItemCart() {
        addCart.setOnClickListener {
            val item = CartBeerItem(beerItemViewModel.getBeerItem!!)
            beerCartViewModel.addItem(item)
            Toast.makeText(requireContext(), "${item.beerItem.name}, add in cart", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_beerInfoFragment_to_SecondFragment)
        }
    }
}