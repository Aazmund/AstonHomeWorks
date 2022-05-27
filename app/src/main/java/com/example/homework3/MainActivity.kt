package com.example.homework3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTask1: Button = findViewById(R.id.buttonTask1)
        val btnTask2: Button = findViewById(R.id.buttonTask2)

        btnTask1.setOnClickListener {
            val intentTask1 = Intent(this, ActivityTask1::class.java)
            startActivity(intentTask1)
        }

        btnTask2.setOnClickListener {
            val intentTask2 = Intent(this, ActivityTask2::class.java)
            startActivity(intentTask2)
        }
    }
}