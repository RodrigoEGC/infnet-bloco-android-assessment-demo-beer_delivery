package com.noplayer.assessmentdemobeerdelivery.fragment

import android.content.Intent
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
import com.noplayer.assessmentdemobeerdelivery.ResultActivity
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
import com.noplayer.assessmentdemobeerdelivery.model.CartBeerItem
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerCartViewModel
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerListViewModel
import kotlinx.android.synthetic.main.fragment_beer_info.*
import java.lang.Exception

class BeerInfoFragment : Fragment() {
    private lateinit var viewModelFactory : ViewModelFactory
    private lateinit var beerListViewModel : BeerListViewModel
    private lateinit var beerCartViewModel: BeerCartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory()

        activity?.let {
            beerListViewModel = ViewModelProvider(it, viewModelFactory).get(BeerListViewModel::class.java)
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
            val beerItem = beerListViewModel.getBeerItem!!

            if (beerItem.isHighlyRated) {
                highly_rated_icon.visibility = View.VISIBLE
            }

            beer_info_name.text = beerItem.name
            beer_info_description.text = beerItem.description
            beer_info_price.text = beerItem.price.toString()
            beer_info_photo.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    beerItem.photo
                )
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Unknown beer", Toast.LENGTH_SHORT).show()
        }
    }


    private fun addItemCart() {
        add_cart.setOnClickListener {
            val item = CartBeerItem(beerListViewModel.getBeerItem!!)
            beerCartViewModel.addItem(item)
            Toast.makeText(requireContext(), "${item.beerItem.name}, add in cart", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_beerInfoFragment_to_SecondFragment)
        }
    }

    private fun buyItem() {
        buy.setOnClickListener {
            val url = beerListViewModel.getBeerItem!!.url
            val item = beerListViewModel.getBeerItem!!.name

            val intent = Intent(activity, ResultActivity::class.java)

            intent.putExtra("ItemInfoFragment", item)
            intent.putExtra("UrlInfoFragment", url)

            startActivity(intent)
        }
    }
}