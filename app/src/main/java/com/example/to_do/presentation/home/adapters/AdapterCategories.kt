package com.example.to_do.presentation.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemCategorySelectedBinding

class AdapterCategories(private var categoriesList: ArrayList<String>)
    : RecyclerView.Adapter<AdapterCategories.ViewHolderCategories>() {

    var selectItem = 0
    var onUserClick: OnUserClick ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategories {
        val binding = ItemCategorySelectedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCategories(binding)
    }

    override fun getItemCount(): Int = categoriesList.size

    @SuppressLint("ResourceAsColor")
    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: ViewHolderCategories, position: Int) {
        holder.bind(categoriesList[position])
        holder.binding.apply {
            categoryName.setTextColor(R.color.white)
            cardView.setCardBackgroundColor (
                cardView.resources.getColor (
                    if (selectItem == position) R.color.blue
                    else R.color.white
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolderCategories(val binding: ItemCategorySelectedBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                selectItem = layoutPosition
                onUserClick?.onClick(categoriesList[layoutPosition])
                notifyDataSetChanged()
            }
        }

        fun bind(name: String) {
            binding.categoryName.text = name
        }

    }

    interface OnUserClick {
        fun onClick (name: String)
    }
}