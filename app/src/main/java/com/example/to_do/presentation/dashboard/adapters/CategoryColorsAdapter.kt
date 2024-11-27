package com.example.to_do.presentation.dashboard.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemCategoryColorBinding
import com.example.to_do.util.Constants.BROWN_COLOR
import com.example.to_do.util.Constants.GREEN_COLOR
import com.example.to_do.util.Constants.MINT_GREEN_COLOR
import com.example.to_do.util.Constants.ORANGE_COLOR
import com.example.to_do.util.Constants.PINK_COLOR
import com.example.to_do.util.Constants.PURPLE_COLOR
import com.example.to_do.util.Constants.RED_COLOR
import com.example.to_do.util.Constants.SALMON_COLOR
import com.example.to_do.util.Constants.YELLOW_COLOR

class CategoryColorsAdapter (
    private var colorsList : ArrayList<String>
) : RecyclerView.Adapter<CategoryColorsAdapter.CategoryColorsViewHolder>() {

    private var selectItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryColorsViewHolder {
        val binding = ItemCategoryColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryColorsViewHolder(binding)
    }

    override fun getItemCount(): Int = colorsList.size

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryColorsViewHolder, position: Int) {
        holder.bind(colorsList[position])
    }

    inner class CategoryColorsViewHolder(val binding: ItemCategoryColorBinding)
        : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    selectItem = layoutPosition
                }
            }

            @Suppress("DEPRECATION")
            fun bind(color: String) {
                binding.circle.apply {
                    setBackgroundColor (
                        resources.getColor (
                            when (color) {
                                YELLOW_COLOR -> R.color.yellow
                                GREEN_COLOR -> R.color.green
                                MINT_GREEN_COLOR -> R.color.mint_green
                                ORANGE_COLOR -> R.color.orange
                                PURPLE_COLOR -> R.color.purple
                                BROWN_COLOR -> R.color.brown
                                RED_COLOR -> R.color.red
                                PINK_COLOR -> R.color.pink
                                SALMON_COLOR -> R.color.salmon
                                else -> R.color.blue
                            }
                        )
                    )
                }
            }
    }


}