package com.lauratoro.galeriamultimedia

import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.net.Uri
import android.graphics.Bitmap

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(uri)
        }
    }

    private val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        bitmap?.let {
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView = findViewById(R.id.imgView)
        val selectButton = findViewById<Button>(R.id.selectButtonImg)
        val captureButton = findViewById<Button>(R.id.captureButtonP)

        selectButton.setOnClickListener{
            getContent.launch("image/*")
        }

        captureButton.setOnClickListener {
            takePicturePreview.launch(null)
        }
    }


}