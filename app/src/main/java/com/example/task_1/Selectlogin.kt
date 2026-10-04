package com.example.task_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Selectlogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selectlogin)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            findViewById<ImageView>(R.id.btnBack).let { btn ->
                val params = btn.layoutParams as ConstraintLayout.LayoutParams
                params.topMargin = systemBars.top + 12
                btn.layoutParams = params
            }

            findViewById<Button>(R.id.btnSubmit).let { btn ->
                val params = btn.layoutParams as ConstraintLayout.LayoutParams
                params.bottomMargin = systemBars.bottom + 24
                btn.layoutParams = params
            }

            insets
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        val tvZoneValue = findViewById<TextView>(R.id.tvZoneValue)
        val tvAreaValue = findViewById<TextView>(R.id.tvAreaValue)

        findViewById<View>(R.id.rowZone).setOnClickListener {
            val zones = arrayOf("Banasree", "Gulshan", "Dhanmondi", "Uttara", "Mirpur")
            AlertDialog.Builder(this)
                .setTitle("Select Zone")
                .setItems(zones) { _, which ->
                    tvZoneValue.text = zones[which]
                    tvZoneValue.setTextColor(resources.getColor(android.R.color.black, theme))
                }
                .show()
        }

        findViewById<View>(R.id.rowArea).setOnClickListener {
            val areas = arrayOf("Block A", "Block B", "Block C", "Block D", "Sector 1")
            AlertDialog.Builder(this)
                .setTitle("Select Area")
                .setItems(areas) { _, which ->
                    tvAreaValue.text = areas[which]
                    tvAreaValue.setTextColor(resources.getColor(android.R.color.black, theme))
                }
                .show()
        }

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val selectedArea = tvAreaValue.text.toString()
            if (selectedArea == "Types of your area") {
                Toast.makeText(this, "Please select your area first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this@Selectlogin, log_in::class.java)
            startActivity(intent)
        }
    }
}