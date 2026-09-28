package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class signin_page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            insets
        }

        val phoneInput = findViewById<View>(R.id.phoneInputContainer)
        phoneInput.setOnClickListener {
            val intent = Intent(this@signin_page, Number::class.java)
            startActivity(intent)
        }

        val googleButton = findViewById<Button>(R.id.googlebutton)
        googleButton.setOnClickListener {
            val intent = Intent(this@signin_page, Number::class.java)
            startActivity(intent)
        }

        val facebookButton = findViewById<Button>(R.id.facebookbutton)
        facebookButton.setOnClickListener {
            val intent = Intent(this@signin_page, Number::class.java)
            startActivity(intent)
        }
    }
}