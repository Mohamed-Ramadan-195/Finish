package com.example.to_do.presentation.create.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemCategorySelectedBinding
import com.example.to_do.domain.model.Category

class AdapterCategorySelected : RecyclerView.Adapter<AdapterCategorySelected.ViewHolderCategorySelected>() {

    var categoriesList: MutableList<Category> = mutableListOf()
    var onUserClick : OnUserClick? = null
    var selectItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategorySelected {
        val binding = ItemCategorySelectedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCategorySelected(binding)
    }

    override fun getItemCount(): Int = categoriesList.size

    @Suppress("DEPRECATION")
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolderCategorySelected, position: Int) {
        holder.bind(categoriesList[position])
        holder.binding.apply {
            if (selectItem == position) {
                cardView.setCardBackgroundColor(holder.binding.cardView.resources.getColor(R.color.blue))
                categoryName.setTextColor(holder.binding.cardView.resources.getColor(R.color.white))
            } else {
                cardView.setCardBackgroundColor(holder.binding.cardView.resources.getColor(R.color.white))
                categoryName.setTextColor(holder.binding.cardView.resources.getColor(R.color.black))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolderCategorySelected(val binding : ItemCategorySelectedBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                selectItem = layoutPosition
                onUserClick?.onClick(categoriesList[layoutPosition])
                notifyDataSetChanged()
            }
        }

        fun bind(category: Category) {
            binding.categoryName.text = category.categoryName
        }
    }

    interface OnUserClick {
        fun onClick(category: Category)
    }

}