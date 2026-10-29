package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.button.MaterialButton

class Selectlogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selectlogin)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)
        val tvZoneValue = findViewById<TextView>(R.id.tvZoneValue)
        val tvAreaValue = findViewById<TextView>(R.id.tvAreaValue)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            btnBack.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + 16
            }

            btnSubmit.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = systemBars.bottom + 24
            }

            insets
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<View>(R.id.rowZone).setOnClickListener {
            val zones = arrayOf("Banasree", "Gulshan", "Dhanmondi", "Uttara", "Mirpur")
            AlertDialog.Builder(this)
                .setTitle("Select Zone")
                .setItems(zones) { _, which ->
                    tvZoneValue.text = zones[which]
                    tvZoneValue.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                }
                .show()
        }

        findViewById<View>(R.id.rowArea).setOnClickListener {
            val areas = arrayOf("Block A", "Block B", "Block C", "Block D", "Sector 1")
            AlertDialog.Builder(this)
                .setTitle("Select Area")
                .setItems(areas) { _, which ->
                    tvAreaValue.text = areas[which]
                    tvAreaValue.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                }
                .show()
        }

        btnSubmit.setOnClickListener {
            val selectedArea = tvAreaValue.text.toString().trim()
            if (selectedArea.equals("Types of your area", ignoreCase = true)) {
                Toast.makeText(this, "Please select your area first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this@Selectlogin, log_in::class.java)
            startActivity(intent)
        }
    }
}