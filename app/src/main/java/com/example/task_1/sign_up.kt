package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.button.MaterialButton

class sign_up : AppCompatActivity() {

    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val ivEmailCheck = findViewById<ImageView>(R.id.ivEmailCheck)
        val ivTogglePassword = findViewById<ImageView>(R.id.ivTogglePassword)
        val btnSignUp = findViewById<MaterialButton>(R.id.sign_upbtn)
        val tvLoginLink = findViewById<TextView>(R.id.tvLoginLink)

        // Keep top background edge-to-edge; adjust back button & keyboard scroll offset
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())

            btnBack.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + 16
            }

            findViewById<android.view.View>(R.id.scrollContainer).setPadding(
                0,
                0,
                0,
                maxOf(systemBars.bottom, ime.bottom)
            )

            insets
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Show green checkmark when email is valid
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val input = s?.toString()?.trim().orEmpty()
                ivEmailCheck.visibility = if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                    android.view.View.VISIBLE
                } else {
                    android.view.View.GONE
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Toggle password show/hide
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            etPassword.transformationMethod = if (isPasswordVisible) {
                HideReturnsTransformationMethod.getInstance()
            } else {
                PasswordTransformationMethod.getInstance()
            }
            etPassword.setSelection(etPassword.text.length)
        }

        tvLoginLink.setOnClickListener {
            val intent = Intent(this@sign_up, log_in::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty()) {
                etUsername.error = "Please enter your username"
                etUsername.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Please enter a valid email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this@sign_up, home_screen_2::class.java)
            startActivity(intent)
            finish()
        }
    }
}