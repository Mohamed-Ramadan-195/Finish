package com.example.to_do.presentation.profile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.to_do.presentation.profile.adapters.CategoryColorsAdapter
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCreateCategoryBinding
import com.example.to_do.util.Constants.BROWN_COLOR
import com.example.to_do.util.Constants.GREEN_COLOR
import com.example.to_do.util.Constants.MINT_GREEN_COLOR
import com.example.to_do.util.Constants.ORANGE_COLOR
import com.example.to_do.util.Constants.PINK_COLOR
import com.example.to_do.util.Constants.PURPLE_COLOR
import com.example.to_do.util.Constants.RED_COLOR
import com.example.to_do.util.Constants.SALMON_COLOR
import com.example.to_do.util.Constants.YELLOW_COLOR

class CreateCategoryFragment : BaseFragment<FragmentCreateCategoryBinding>() {

    private val colorsList = ArrayList<String>()
    private var categoryColorsAdapter: CategoryColorsAdapter? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateCategoryBinding {
        return FragmentCreateCategoryBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setCategoryColors()
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener {  }
            createCategoryButton.setOnClickListener { validate() }
        }
    }

    private fun setCategoryColors() {
        if (colorsList.size == 0) {
            colorsList.apply {
                add(YELLOW_COLOR)
                add(GREEN_COLOR)
                add(MINT_GREEN_COLOR)
                add(ORANGE_COLOR)
                add(PURPLE_COLOR)
                add(BROWN_COLOR)
                add(RED_COLOR)
                add(PINK_COLOR)
                add(SALMON_COLOR)
            }
            categoryColorsAdapter = CategoryColorsAdapter(colorsList)
            binding.colorsRecyclerView.adapter = categoryColorsAdapter
        }
    }

    private fun validate() {
        binding.apply {

        }
    }

}