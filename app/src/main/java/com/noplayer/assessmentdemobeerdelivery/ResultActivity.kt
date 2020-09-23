package com.noplayer.assessmentdemobeerdelivery

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setInfoView()

        btn_add_form_pay.setOnClickListener {
            Snackbar.make(
                it,"Demo Finished! Thank you for using Beer Delivery!", Snackbar.LENGTH_LONG
            ).show()
        }

        btn_back_list.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun openChrome(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            intent.setPackage(null)
            Toast.makeText(this, "No browser in your device", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setInfoView() {

        val nameInfoFragment: String? =  intent.getStringExtra("ItemInfoFragment")

        val urlInfoFragment: String? =  intent.getStringExtra("UrlInfoFragment")

        val name: String? =  intent.getStringExtra("Name")

        val url: String? =  intent.getStringExtra("Url")

        if (nameInfoFragment != null) {
            info_detail_beer_Item.text = nameInfoFragment

            btn_more_beer.setOnClickListener {
                openChrome(urlInfoFragment!!)
            }
        }

        if (name != null) {
            info_detail_beer_Item.text = name

            btn_more_beer.setOnClickListener {
                openChrome(url!!)
            }
        }

    }

}