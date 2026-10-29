package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class Favourites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favourites)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val addAllBtn = findViewById<MaterialButton>(R.id.addAllBtn)

        // Pre-select Favourite Tab
        bottomNav.selectedItemId = R.id.nav_favourite

        // System bar insets without breaking top alignment or bottom tabs
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            tvTitle.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + 16
            }

            bottomNav.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom
            }

            insets
        }

        addAllBtn.setOnClickListener {
            Toast.makeText(this, "All items added to cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Favourites, My_Cart::class.java)
            startActivity(intent)
            finish()
        }

        // Row clicks leading to Product Details
        findViewById<android.view.View>(R.id.itemSprite).setOnClickListener {
            startActivity(Intent(this, Product_details::class.java))
        }

        // Bottom Navigation Tab Switching
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_favourite -> true
                R.id.nav_shop -> {
                    startActivity(Intent(this@Favourites, home_screen_2::class.java))
                    finish()
                    true
                }
                R.id.nav_explore -> {
                    startActivity(Intent(this@Favourites, Explore::class.java))
                    finish()
                    true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this@Favourites, My_Cart::class.java))
                    finish()
                    true
                }
                R.id.nav_account -> {
                    true
                }
                else -> false
            }
        }
    }
}