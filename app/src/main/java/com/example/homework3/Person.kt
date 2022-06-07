package com.example.homework3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.util.*


class Person(
    var firstName: String,
    var secondName: String,
    var number: String,
    var bitmap: Bitmap
) {

    companion object {
        var persons: MutableList<Person> = emptyList<Person>().toMutableList()

        fun changePersonByIndex(
            index: Int,
            firstName: String,
            secondName: String,
            number: String,
            bitmap: Bitmap
        ) {
            persons[index].firstName = firstName
            persons[index].secondName = secondName
            persons[index].number = number
            persons[index].bitmap = bitmap
        }

        fun generatePersonsList(
            contactsName: List<String>,
            contactsNumber: List<String>,
            context: Context
        ) {

            val bitmap: Bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)

            for (i in 0 until 11) {

                for (contact in contactsName) {

                    val list: List<String> = contact.split(" ")
                    val person = Person(
                        list[1],
                        list[0],
                        contactsNumber[contactsName.indexOf(contact)],
                        bitmap
                    )
                    persons.add(person)
                }
            }
            uploadImg(context)
        }

        private fun uploadImg(context: Context) {
            var url = "https://picsum.photos/300/300?random=1"
            for (person in persons){
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .into(object : CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            person.bitmap = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
                val rnd = Random()
                rnd.nextInt(5)
                url += rnd
            }
        }
    }
}