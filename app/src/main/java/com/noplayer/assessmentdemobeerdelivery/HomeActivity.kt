package com.noplayer.assessmentdemobeerdelivery

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.noplayer.assessmentdemobeerdelivery.factory.ViewModelFactory
import com.noplayer.assessmentdemobeerdelivery.viewModel.BeerItemViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var beerItemViewModel : BeerItemViewModel

    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModelFactory = ViewModelFactory()

        beerItemViewModel = ViewModelProvider(this, viewModelFactory).get(BeerItemViewModel::class.java)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}