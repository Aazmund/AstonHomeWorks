package com.example.homework3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CustomRecyclerAdapter(
    private var persons:List<Person>,
    private val onItemClicked: (position: Int, type:String) -> Unit): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    private var personFilteredList: MutableList<Person> = persons as MutableList<Person>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    class MyViewHolder(
        itemView: View,
        private val onItemClicked: (position: Int, type:String) -> Unit,
    ): RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        val img: ImageView = itemView.findViewById(R.id.contactImg)
        val firstName: TextView = itemView.findViewById(R.id.textViewFirstName)
        val secondName: TextView = itemView.findViewById(R.id.textViewSecondName)
        val number: TextView = itemView.findViewById(R.id.textViewNumber)

        fun bind(person: Person){
            img.setImageBitmap(person.bitmap)
            firstName.text = person.firstName
            secondName.text = person.secondName
            number.text = person.number
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            onItemClicked(position, "click")
        }

        override fun onLongClick(p0: View?): Boolean {
            val position = adapterPosition
            onItemClicked(position, "longclick")
            return true
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(charSearch: String){
        if(charSearch == ""){
            persons = Person.persons
            this.notifyDataSetChanged()
        }else{
            personFilteredList = persons.filter {
                    it.firstName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) ||
                    it.secondName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) ||
                    (it.firstName.lowercase(Locale.ROOT) + " " + it.secondName.lowercase(Locale.ROOT)).contains(charSearch.lowercase(Locale.ROOT))
            }.toMutableList()
            persons = personFilteredList
            this.notifyDataSetChanged()
        }
    }

    fun setData(newPersonList: List<Person>){
        persons = newPersonList
    }
}
