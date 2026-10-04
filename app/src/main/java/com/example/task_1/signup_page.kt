package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signup_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        val navigateToNumber = {
            val intent = Intent(this, Number::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.phoneInputContainer).setOnClickListener { navigateToNumber() }
        findViewById<Button>(R.id.btnGoogle).setOnClickListener { navigateToNumber() }
        findViewById<Button>(R.id.btnFacebook).setOnClickListener { navigateToNumber() }
    }
}