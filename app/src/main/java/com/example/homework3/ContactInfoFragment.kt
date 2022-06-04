package com.example.homework3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class ContactInfoFragment : Fragment() {
    private var name: String = ""
    private var number: String = ""
    private var position: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println(it)
            name = it.getString("name").toString()
            number = it.getString("number").toString()
            position = it.getString("position").toString()
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

        val nameEdit: EditText = view.findViewById<EditText?>(R.id.editName).apply {
            isFocusable = false
            isLongClickable = false
        }
        val secondNameEdit: EditText = view.findViewById<EditText?>(R.id.editSecondName).apply {
            isFocusable = false
            isLongClickable = false
        }
        val numberEdit: EditText = view.findViewById<EditText?>(R.id.editNumber).apply {
            isFocusable = false
            isLongClickable = false
        }
        val btn: Button = view.findViewById(R.id.btnSave)

        val list: List<String> = name.split(" ")

        nameEdit.setText(list[1])
        secondNameEdit.setText(list[0])
        numberEdit.setText(number)

        btn.setOnClickListener{
            if(btn.text.equals("Сохранить")){
                Person.changePersonByIndex(position.toInt(), nameEdit.text.toString(), secondNameEdit.text.toString(), numberEdit.text.toString())
                val fragmentManager = parentFragmentManager
                fragmentManager.popBackStack()

            }else{
                btn.text = "Сохранить"

                nameEdit.isFocusable = true
                nameEdit.isFocusableInTouchMode = true
                nameEdit.isLongClickable = true
                secondNameEdit.isFocusable = true
                secondNameEdit.isLongClickable = true
                secondNameEdit.isFocusableInTouchMode = true
                numberEdit.isFocusable = true
                numberEdit.isLongClickable = true
                numberEdit.isFocusableInTouchMode = true
            }
        }

    }
}