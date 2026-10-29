package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Verification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verification)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnNext = findViewById<FloatingActionButton>(R.id.btnNext)
        val tvResendCode = findViewById<TextView>(R.id.tvResendCode)
        val etCodeInput = findViewById<EditText>(R.id.etCodeInput)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvResendCode.setOnClickListener {
            Toast.makeText(this, "Verification code resent", Toast.LENGTH_SHORT).show()
        }

        btnNext.setOnClickListener {
            val code = etCodeInput.text.toString().trim()
            if (code.length < 4) {
                etCodeInput.error = "Enter a valid 4-digit code"
                etCodeInput.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this@Verification, Selectlogin::class.java)
            startActivity(intent)
        }
    }
}