package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomnavigation.BottomNavigationView

class Beverages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beverages)

        val headerLayout = findViewById<View>(R.id.headerLayout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val ivFilter = findViewById<ImageView>(R.id.ivFilter)

        // Highlights Explore tab as Beverages falls under Explore
        bottomNav.selectedItemId = R.id.nav_explore

        // Handle edge-to-edge system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            headerLayout.setPadding(
                headerLayout.paddingLeft,
                systemBars.top + 16,
                headerLayout.paddingRight,
                headerLayout.paddingBottom
            )

            bottomNav.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom
            }

            insets
        }

        ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ivFilter.setOnClickListener {
            Toast.makeText(this, "Filter clicked", Toast.LENGTH_SHORT).show()
        }

        val openDetails = View.OnClickListener {
            startActivity(Intent(this, Product_details::class.java))
        }

        findViewById<View>(R.id.cardDietCoke).setOnClickListener(openDetails)
        findViewById<View>(R.id.cardSprite).setOnClickListener(openDetails)
        findViewById<View>(R.id.cardJuice).setOnClickListener(openDetails)
        findViewById<View>(R.id.cardOrangeJuice).setOnClickListener(openDetails)
        findViewById<View>(R.id.cardCocaCola).setOnClickListener(openDetails)
        findViewById<View>(R.id.cardPepsi).setOnClickListener(openDetails)

        val onAddToCart = View.OnClickListener {
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnAddDietCoke).setOnClickListener(onAddToCart)
        findViewById<View>(R.id.btnAddSprite).setOnClickListener(onAddToCart)
        findViewById<View>(R.id.btnAddJuice).setOnClickListener(onAddToCart)
        findViewById<View>(R.id.btnAddOrangeJuice).setOnClickListener(onAddToCart)
        findViewById<View>(R.id.btnAddCocaCola).setOnClickListener(onAddToCart)
        findViewById<View>(R.id.btnAddPepsi).setOnClickListener(onAddToCart)

        // Bottom Navigation Transitions
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_explore -> {
                    finish()
                    true
                }
                R.id.nav_shop -> {
                    startActivity(Intent(this@Beverages, home_screen_2::class.java))
                    finish()
                    true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this@Beverages, My_Cart::class.java))
                    finish()
                    true
                }
                R.id.nav_favourite -> {
                    startActivity(Intent(this@Beverages, Favourites::class.java))
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