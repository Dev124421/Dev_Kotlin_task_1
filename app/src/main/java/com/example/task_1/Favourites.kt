package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Favourites : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favourites)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val addAllBtn = findViewById<Button>(R.id.addAllBtn)
        addAllBtn.setOnClickListener {
            val intent = Intent(this@Favourites, My_Cart::class.java)
            startActivity(intent)
            finish()
        }

        val navShop = findViewById<View>(R.id.navShop)
        navShop.setOnClickListener {
            val intent = Intent(this@Favourites, home_screen_2::class.java)
            startActivity(intent)
            finish()
        }

        val navExplore = findViewById<View>(R.id.navExplore)
        navExplore.setOnClickListener {
            val intent = Intent(this@Favourites, Explore::class.java)
            startActivity(intent)
            finish()
        }

        val navCart = findViewById<View>(R.id.navCart)
        navCart.setOnClickListener {
            val intent = Intent(this@Favourites, My_Cart::class.java)
            startActivity(intent)
            finish()
        }
    }
}