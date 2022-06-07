package com.example.homework3

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil(
    private val oldList: List<Person>,
    private val newList: List<Person>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].firstName == newList[newItemPosition].firstName &&
                oldList[oldItemPosition].secondName == newList[newItemPosition].secondName &&
                oldList[oldItemPosition].number == newList[newItemPosition].number &&
                oldList[oldItemPosition].bitmap == newList[newItemPosition].bitmap
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].firstName == newList[newItemPosition].firstName &&
                oldList[oldItemPosition].secondName == newList[newItemPosition].secondName &&
                oldList[oldItemPosition].number == newList[newItemPosition].number &&
                oldList[oldItemPosition].bitmap == newList[newItemPosition].bitmap
    }
}