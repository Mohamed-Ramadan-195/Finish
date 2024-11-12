package com.example.to_do.presentation.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemCategoryBinding

class AdapterCategories (
    private var categoriesList : ArrayList<String>
) : RecyclerView.Adapter<AdapterCategories.ViewHolderCategories>() {

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
            binding.root.setOnClickListener {  }
        }

        fun bind(category : String) {
            binding.category.text = category
        }

    }

}