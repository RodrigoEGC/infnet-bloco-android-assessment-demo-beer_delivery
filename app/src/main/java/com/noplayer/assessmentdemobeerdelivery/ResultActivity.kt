package com.noplayer.assessmentdemobeerdelivery

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private val galleryRequestCode = 123
    private val LOCATION_PERMISSION_REQUEST_CODE = 2000
    private var pictureTaken: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        btnIntent.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Pick an image"), galleryRequestCode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == galleryRequestCode) {
                // now we can get the thumbnail
                val imageBitmap = data!!.extras!!.get("data") as Bitmap
                imageView.setImageBitmap(imageBitmap)
            }

        }

        fun prepOpenImageGallery() {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
                startActivityForResult(this, galleryRequestCode)
            }
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            when (requestCode) {
                LOCATION_PERMISSION_REQUEST_CODE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    } else {
                        Toast.makeText(
                            this,
                            "Unable to update location without permission",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else -> {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                }
            }
        }
    }

}