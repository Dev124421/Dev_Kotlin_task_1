package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home_screen_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val cardRedApple = findViewById<View>(R.id.cardRedApple)
        cardRedApple.setOnClickListener {
            val intent = Intent(this@home_screen_2, Product_details::class.java)
            startActivity(intent)
        }

        val cardOrganicBananas = findViewById<View>(R.id.cardOrganicBananas)
        cardOrganicBananas?.setOnClickListener {
            val intent = Intent(this@home_screen_2, Product_details::class.java)
            startActivity(intent)
        }

        val navExplore = findViewById<View>(R.id.navExplore)
        navExplore.setOnClickListener {
            val intent = Intent(this@home_screen_2, Explore::class.java)
            startActivity(intent)
        }

        val navCart = findViewById<View>(R.id.navCart)
        navCart.setOnClickListener {
            val intent = Intent(this@home_screen_2, My_Cart::class.java)
            startActivity(intent)
        }

        val navFavourite = findViewById<View>(R.id.navFavourite)
        navFavourite.setOnClickListener {
            val intent = Intent(this@home_screen_2, Favourites::class.java)
            startActivity(intent)
        }
    }
}