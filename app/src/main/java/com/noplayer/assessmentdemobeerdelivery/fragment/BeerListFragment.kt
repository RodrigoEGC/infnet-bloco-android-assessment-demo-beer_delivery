package com.noplayer.assessmentdemobeerdelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.noplayer.assessmentdemobeerdelivery.R
import com.noplayer.assessmentdemobeerdelivery.adapter.BeerRecyclerAdapter
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerListViewModel
import kotlinx.android.synthetic.main.fragment_beer_list.*

class BeerListFragment : Fragment() {
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var beerListViewModel : BeerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory = ViewModelFactory()

        activity?.let {
            beerListViewModel = ViewModelProvider(it, viewModelFactory).get(BeerListViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_beer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        beerRecyclerView.adapter = BeerRecyclerAdapter(beerListViewModel.all()) {
            beerListViewModel.getBeerItem = it
            findNavController().navigate(R.id.action_FirstFragment_to_beerInfoFragment)
        }

        beerRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

}