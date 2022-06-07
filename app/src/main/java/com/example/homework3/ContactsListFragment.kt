package com.example.homework3

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsListFragment : Fragment() {

    private lateinit var myCustomRecyclerAdapter: CustomRecyclerAdapter

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

        val searchView:SearchView = view.findViewById(R.id.person_search)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        myCustomRecyclerAdapter = CustomRecyclerAdapter(Person.persons){
            position, str -> onListItemClick(position, str)
        }

        recyclerView.addItemDecoration(CustomItemDecorator(view.context))

        recyclerView.adapter = myCustomRecyclerAdapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                myCustomRecyclerAdapter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                myCustomRecyclerAdapter.filter(newText)
                return false
            }

        })
    }

    private fun onListItemClick(position: Int, str: String) {
        if (str == "click"){
            val bundle = Bundle().apply {
                putString("firstName", Person.persons[position].firstName)
                putString("secondName", Person.persons[position].secondName)
                putString("number", Person.persons[position].number)
                putString("position", position.toString())
                putParcelable("img", Person.persons[position].bitmap)
            }
            val contactInfoFragment = ContactInfoFragment().apply {
                arguments = bundle
            }
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.contactFragment, contactInfoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }else{
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("Удалить контакт?")
                .setCancelable(false)
                .setPositiveButton("Да", DialogInterface.OnClickListener{
                        dialog, id -> finish(position, myCustomRecyclerAdapter)
                })
                .setNegativeButton("Нет", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Удаление контакта")
            alert.show()
        }

    }

    private fun finish(position: Int, myCustomRecyclerAdapter: CustomRecyclerAdapter) {
        Person.persons.removeAt(position)
        myCustomRecyclerAdapter.notifyItemRemoved(position)

        //diffutil
//        var newPersonList = Person.persons
//        newPersonList.removeAt(position)
//
//        val diffUtilCallback = MyDiffUtil(Person.persons, newPersonList)
//        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
//
//        myCustomRecyclerAdapter.setData(newPersonList)
//        diffResult.dispatchUpdatesTo(myCustomRecyclerAdapter)
    }
}