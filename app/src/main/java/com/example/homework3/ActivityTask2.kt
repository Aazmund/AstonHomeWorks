package com.example.homework3

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ActivityTask2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        val imageView: ImageView = findViewById(R.id.imageView)
        val editText: EditText = findViewById(R.id.editText)

        editText.setOnKeyListener(View.OnKeyListener{ v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val url = editText.text.toString()
                Glide.with(applicationContext)
                    .load(url)
                    .placeholder(imageView.drawable)
                    .error(Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show())
                    .into(imageView)

                return@OnKeyListener true
            }
            false
        })
    }
}