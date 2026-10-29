package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

class Product_details : AppCompatActivity() {

    private var quantity = 1
    private val unitPrice = 4.99
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_details)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnShare = findViewById<ImageView>(R.id.btnShare)
        val btnAddToBasket = findViewById<AppCompatButton>(R.id.btnAddToBasket)
        val ivFavourite = findViewById<ImageView>(R.id.ivFavourite)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
        val btnMinus = findViewById<TextView>(R.id.btnMinus)
        val btnPlus = findViewById<TextView>(R.id.btnPlus)
        val rowProductDetail = findViewById<View>(R.id.rowProductDetail)
        val tvProductDesc = findViewById<TextView>(R.id.tvProductDesc)
        val ivArrowDetail = findViewById<ImageView>(R.id.ivArrowDetail)

        // System bar insets without padding the root layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Offset top action icons below status bar
            btnBack.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + 16
            }
            btnShare.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + 16
            }

            // Offset bottom button above navigation bar
            btnAddToBasket.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom + 24
            }

            insets
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check out this product")
                putExtra(Intent.EXTRA_TEXT, "Naturel Red Apple - $4.99 on Nectar")
            }
            startActivity(Intent.createChooser(shareIntent, "Share Product"))
        }

        // Quantity counter & price update
        btnPlus.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
            tvPrice.text = "$${String.format("%.2f", unitPrice * quantity)}"
        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQuantity.text = quantity.toString()
                tvPrice.text = "$${String.format("%.2f", unitPrice * quantity)}"
            }
        }

        // Favorite Toggle
        ivFavourite.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                ivFavourite.setColorFilter(getColor(R.color.green))
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                ivFavourite.clearColorFilter()
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            }
        }

        // Expand / Collapse Product Detail
        rowProductDetail.setOnClickListener {
            if (tvProductDesc.visibility == View.VISIBLE) {
                tvProductDesc.visibility = View.GONE
                ivArrowDetail.rotation = 180f
            } else {
                tvProductDesc.visibility = View.VISIBLE
                ivArrowDetail.rotation = 270f
            }
        }

        btnAddToBasket.setOnClickListener {
            Toast.makeText(this, "Added $quantity item(s) to basket", Toast.LENGTH_SHORT).show()
        }
    }
}