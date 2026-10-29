package com.example.task_1

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class home_screen_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen2)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val nestedScrollView = findViewById<androidx.core.widget.NestedScrollView>(R.id.nestedScrollView)

        // Inset handling for status bar & navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            nestedScrollView.setPadding(0, systemBars.top, 0, 0)
            bottomNav.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom
            }

            insets
        }

        // Setup Datasets
        val exclusiveProducts = listOf(
            Product("1", "Organic Bananas", "12 pieces", "$4.99", R.drawable.banana),
            Product("2", "Red Apple", "1 kg", "$4.99", R.drawable.product_1),
            Product("3", "Ginger", "250 gm", "$4.99", R.drawable.ginger)
        )

        val bestSellingProducts = listOf(
            Product("4", "Bell Pepper Red", "1 kg", "$4.99", R.drawable.red_pepper),
            Product("5", "Ginger", "250 gm", "$4.99", R.drawable.ginger)
        )

        val groceryProducts = listOf(
            Product("6", "Beef Bone", "1 kg", "$4.99", R.drawable.beef_bone),
            Product("7", "Broiler Chicken", "1 kg", "$4.99", R.drawable.broiler_chicken)
        )

        val clickHandler: (Product) -> Unit = { product ->
            Toast.makeText(this, "Clicked: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        val addHandler: (Product) -> Unit = { product ->
            Toast.makeText(this, "Added to cart: ${product.name}", Toast.LENGTH_SHORT).show()
        }

        // Attach Adapters directly to the RecyclerViews
        findViewById<RecyclerView>(R.id.rvExclusiveOffers).adapter =
            ProductAdapter(exclusiveProducts, clickHandler, addHandler)

        findViewById<RecyclerView>(R.id.rvBestSelling).adapter =
            ProductAdapter(bestSellingProducts, clickHandler, addHandler)

        findViewById<RecyclerView>(R.id.rvGroceries).adapter =
            ProductAdapter(groceryProducts, clickHandler, addHandler)

        // Bottom Navigation Item Clicks
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_shop -> true
                R.id.nav_explore -> {
                    Toast.makeText(this, "Explore selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_cart -> {
                    Toast.makeText(this, "Cart selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_favourite -> {
                    Toast.makeText(this, "Favourite selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_account -> {
                    Toast.makeText(this, "Account selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}