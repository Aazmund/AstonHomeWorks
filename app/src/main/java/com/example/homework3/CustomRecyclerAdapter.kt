package com.example.homework3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val contacts:List<Contact>, private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        val name:TextView = itemView.findViewById(R.id.textViewName)
        val number:TextView = itemView.findViewById(R.id.textViewNumber)

        fun bind(contact: Contact){
            name.text = contact.name
            number.text = contact.number
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }
}

data class Contact(val name: String, val number: String)