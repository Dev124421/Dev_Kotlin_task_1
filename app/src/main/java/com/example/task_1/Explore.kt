package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.bottomnavigation.BottomNavigationView

class Explore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explore)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Highlight Explore Tab
        bottomNav.selectedItemId = R.id.nav_explore

        // Handle Status and Navigation Bar Insets
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

        // Search Bar Click
        findViewById<View>(R.id.staticSearchBar).setOnClickListener {
            Toast.makeText(this, "Search opened", Toast.LENGTH_SHORT).show()
        }

        // Category Cards Navigation
        findViewById<View>(R.id.cardBeverages).setOnClickListener {
            val intent = Intent(this@Explore, Beverages::class.java)
            startActivity(intent)
        }

        val dummyCategoryClick = View.OnClickListener { v ->
            Toast.makeText(this, "Category clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.cardFruits).setOnClickListener(dummyCategoryClick)
        findViewById<View>(R.id.cardOil).setOnClickListener(dummyCategoryClick)
        findViewById<View>(R.id.cardMeat).setOnClickListener(dummyCategoryClick)
        findViewById<View>(R.id.cardBakery).setOnClickListener(dummyCategoryClick)
        findViewById<View>(R.id.cardDairy).setOnClickListener(dummyCategoryClick)

        // Bottom Navigation Transitions
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_explore -> true
                R.id.nav_shop -> {
                    startActivity(Intent(this@Explore, home_screen_2::class.java))
                    finish()
                    true
                }
                R.id.nav_cart -> {
                    startActivity(Intent(this@Explore, My_Cart::class.java))
                    finish()
                    true
                }
                R.id.nav_favourite -> {
                    startActivity(Intent(this@Explore, Favourites::class.java))
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