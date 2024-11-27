package com.example.to_do.presentation.dashboard.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemCategoryBinding
import com.example.to_do.domain.model.Category

class AdapterCategories : RecyclerView.Adapter<AdapterCategories.ViewHolderCategories>() {

    var categoriesList: MutableList<Category> = mutableListOf()
    var onUserClick : OnUserClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategories {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCategories(binding)
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(holder: ViewHolderCategories, position: Int) {
        holder.bind(categoriesList[position])
    }

    inner class ViewHolderCategories (val binding : ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteIcon.setOnClickListener {
                onUserClick?.onClick(layoutPosition)
            }
        }

        fun bind(category : Category) {
            binding.category.text = category.categoryName
        }
    }

    // Update the list and notify changes
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Category>) {
        categoriesList.clear()
        categoriesList.addAll(newList)
        notifyDataSetChanged()
    }

    // Add a single item and notify
    fun addItem(category: Category) {
        categoriesList.add(category)
        notifyItemInserted(categoriesList.size - 1)
    }

    // delete a single item and notify
    fun deleteItem(position: Int) {
        categoriesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, categoriesList.size)
    }

    interface OnUserClick {
        fun onClick(position: Int)
    }
}