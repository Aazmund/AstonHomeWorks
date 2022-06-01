package com.example.homework3

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val colors = arrayOf("Красная, зеленая, синяя", "Все черные", "Черный, серый, желтый")
        val sizes = arrayOf("Стандартные", "Толстые", "Тонкие")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
            spinner.adapter = adapter
        }

        if (spinner2 != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes)
            spinner2.adapter = adapter
        }

        val clock = ClockView(this)
        val layout: ConstraintLayout = findViewById(R.id.constraintLayout)

        layout.addView(clock)

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position){
                    0 -> clock.newColor(Color.BLUE, Color.GREEN, Color.RED)
                    1 -> clock.newColor(Color.BLACK, Color.BLACK, Color.BLACK)
                    2 -> clock.newColor(Color.BLACK, Color.GRAY, Color.YELLOW)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

        spinner2.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position){
                    0 -> clock.newSize(20F, 10F, 5F)
                    1 -> clock.newSize(20F, 20F, 20F)
                    2 -> clock.newSize(5F, 5F, 5F)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }
}