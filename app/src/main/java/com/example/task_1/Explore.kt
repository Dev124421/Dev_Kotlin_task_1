package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Explore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explore)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val navShop = findViewById<View>(R.id.navShop)
        navShop.setOnClickListener {
            val intent = Intent(this@Explore, home_screen_2::class.java)
            startActivity(intent)
            finish()
        }

        val navCart = findViewById<View>(R.id.navCart)
        navCart.setOnClickListener {
            val intent = Intent(this@Explore, My_Cart::class.java)
            startActivity(intent)
            finish()
        }

        val navFavourite = findViewById<View>(R.id.navFavourite)
        navFavourite.setOnClickListener {
            val intent = Intent(this@Explore, Favourites::class.java)
            startActivity(intent)
            finish()
        }
    }
}