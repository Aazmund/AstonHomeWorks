package com.example.homework3

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class ContactInfoFragment : Fragment() {
    private var firstName: String = ""
    private var secondName: String = ""
    private var number: String = ""
    private lateinit var bitmap: Bitmap
    private var position: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstName = it.getString("firstName").toString()
            secondName = it.getString("secondName").toString()
            number = it.getString("number").toString()
            bitmap = it.getParcelable("img")!!
            position = it.getString("position")!!.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgView = view.findViewById<ImageView>(R.id.infoImg).apply {
            setImageBitmap(bitmap)
        }

        val editSecondName = view.findViewById<EditText>(R.id.editSecondName).apply {
            isFocusable = false
            isLongClickable = false
            setText(secondName)
        }

        val editName = view.findViewById<EditText>(R.id.editName).apply {
            isFocusable = false
            isLongClickable = false
            setText(firstName)
        }

        val editNumber = view.findViewById<EditText>(R.id.editNumber).apply {
            isFocusable = false
            isLongClickable = false
            setText(number)
        }

        val button = view.findViewById<Button>(R.id.btnSave)

        button.setOnClickListener {
            if (button.text.equals("Сохранить")) {
                Person.changePersonByIndex(
                    position,
                    editName.text.toString(),
                    editSecondName.text.toString(),
                    editNumber.text.toString(),
                    bitmap
                )
                val fragmentManager = parentFragmentManager
                fragmentManager.popBackStack()

            } else {
                button.text = "Сохранить"

                editName.isFocusable = true
                editName.isFocusableInTouchMode = true
                editName.isLongClickable = true
                editSecondName.isFocusable = true
                editSecondName.isLongClickable = true
                editSecondName.isFocusableInTouchMode = true
                editNumber.isFocusable = true
                editNumber.isLongClickable = true
                editNumber.isFocusableInTouchMode = true
            }
        }
    }
}