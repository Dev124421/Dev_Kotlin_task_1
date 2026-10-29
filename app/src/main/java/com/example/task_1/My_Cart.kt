package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class My_Cart : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var checkoutBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_cart)

        checkoutBtn = findViewById(R.id.checkoutbtn)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val tvTitle = findViewById<android.widget.TextView>(R.id.tvTitle)

        // Highlight Cart in Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_cart

        // Proper system bar insets handling
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

        // Cart Data Setup
        val cartList = mutableListOf(
            CartItem("1", "Bell Pepper Red", "1kg, Price", 4.99, 1, R.drawable.red_pepper),
            CartItem("2", "Egg Chicken Red", "4pcs, Price", 1.99, 1, R.drawable.egg_chicken_red),
            CartItem("3", "Organic Bananas", "12kg, Price", 3.00, 1, R.drawable.banana),
            CartItem("4", "Ginger", "250gm, Price", 2.99, 1, R.drawable.ginger)
        )

        cartAdapter = CartAdapter(cartList) {
            updateCheckoutTotal()
        }

        findViewById<RecyclerView>(R.id.rvCartItems).adapter = cartAdapter
        updateCheckoutTotal()

        checkoutBtn.setOnClickListener {
            val intent = Intent(this@My_Cart, Order_accepted::class.java)
            startActivity(intent)
        }

        // Bottom Navigation Tab Switches
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_cart -> true
                R.id.nav_shop -> {
                    startActivity(Intent(this, home_screen_2::class.java))
                    finish()
                    true
                }
                R.id.nav_explore -> {
                    startActivity(Intent(this, Explore::class.java))
                    finish()
                    true
                }
                R.id.nav_favourite -> {
                    startActivity(Intent(this, Favourites::class.java))
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

    private fun updateCheckoutTotal() {
        val total = cartAdapter.getTotalPrice()
        checkoutBtn.text = "Go to Checkout  $${String.format("%.2f", total)}"
    }
}