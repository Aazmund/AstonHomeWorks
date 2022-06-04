package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until getContactsName().size){
            val names: List<String> = getContactsName()[i].split(" ")
            val person = Person(secondName = names[0], firstName = names[1], number = getContactsNumber()[i])
            Person.persons.add(person)
        }

    }

    private fun getContactsName(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }

    private fun getContactsNumber(): List<String> {
        return this.resources.getStringArray(R.array.numbers).toList()
    }
}