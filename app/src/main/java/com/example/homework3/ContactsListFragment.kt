package com.example.homework3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val listOfContacts:MutableList<Contact> = emptyList<Contact>().toMutableList()

        for (person:Person in Person.persons){
            val contact = Contact(person.firstName + " " + person.secondName, person.number)
            listOfContacts.add(contact)
        }

        val myCustomRecyclerAdapter = CustomRecyclerAdapter(listOfContacts) {
                position -> onListItemClick(position, listOfContacts)
        }

        recyclerView.adapter = myCustomRecyclerAdapter

    }

    @SuppressLint("ResourceType")
    private fun onListItemClick(position: Int, listOfContacts: MutableList<Contact>) {

        val bundle = Bundle().apply {
            putString("name", listOfContacts[position].name)
            putString("number", listOfContacts[position].number)
            putString("position", position.toString())
        }

        val contactInfoFragment = ContactInfoFragment().apply {
            arguments = bundle
        }

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()

        transaction.replace(R.id.contactFragment, contactInfoFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}