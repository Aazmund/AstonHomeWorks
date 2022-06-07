package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Person.generatePersonsList(getContactsName(), getContactsNumber(), applicationContext)
    }

    private fun getContactsName(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }

    private fun getContactsNumber(): List<String> {
        return this.resources.getStringArray(R.array.numbers).toList()
    }
}