package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

class Order_accepted : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order_accepted)

        val btnTrackOrder = findViewById<AppCompatButton>(R.id.btnTrackOrder)
        val tvBackToHome = findViewById<TextView>(R.id.tvBackToHome)

        // Keep bottom action above the system navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            tvBackToHome.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom + 24
            }

            insets
        }

        btnTrackOrder.setOnClickListener {
            Toast.makeText(this, "Tracking your order...", Toast.LENGTH_SHORT).show()
        }

        val navigateToHome = {
            val intent = Intent(this, home_screen_2::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish()
        }

        tvBackToHome.setOnClickListener {
            navigateToHome()
        }
    }
}