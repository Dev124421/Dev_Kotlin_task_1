package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Number : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_number)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val nextBtn = findViewById<FloatingActionButton>(R.id.nextbtn)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)

        // Navigate back to previous screen
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Validate and proceed to verification screen
        nextBtn.setOnClickListener {
            val phone = etPhoneNumber.text.toString().trim()
            if (phone.isEmpty()) {
                etPhoneNumber.error = "Please enter mobile number"
                etPhoneNumber.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this@Number, Verification::class.java).apply {
                putExtra("MOBILE_NUMBER", "+880$phone")
            }
            startActivity(intent)
        }
    }

}