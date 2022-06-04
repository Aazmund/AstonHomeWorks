package com.example.homework3

class Person(var firstName: String, var secondName: String, var number: String){
    companion object{
        val persons: MutableList<Person> = emptyList<Person>().toMutableList()

        fun changePersonByIndex(index: Int, firstName: String, secondName: String, number: String){
            persons[index].firstName = firstName
            persons[index].secondName = secondName
            persons[index].number = number
        }
    }
}