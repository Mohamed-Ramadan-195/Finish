package com.example.to_do.presentation.dashboard.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.CustomDialogAddCategoryBinding
import com.example.to_do.databinding.FragmentDashboardBinding
import com.example.to_do.domain.model.Category
import com.example.to_do.presentation.dashboard.adapters.AdapterCategories
import com.example.to_do.presentation.dashboard.viewmodel.CategoryViewModel
import com.example.to_do.util.Constants.DOWN
import com.example.to_do.util.Constants.UP
import com.example.to_do.util.gone
import com.example.to_do.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private val categoryViewModel : CategoryViewModel by viewModels()
    private val adapterCategories : AdapterCategories by lazy { AdapterCategories() }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        binding.categoriesRecyclerView.adapter = adapterCategories  // set adapter once
        categoryViewModel.getAllCategories() // fetch data
        observers() // observe data
    }

    override fun onClicks() {
        binding.apply {
            addCategory.setOnClickListener {
                showAddCategoryDialog()
            }
            arrowDown.setOnClickListener {
                recyclerViewVisibility(DOWN)
            }
            arrowUp.setOnClickListener {
                recyclerViewVisibility(UP)
            }
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            adapterCategories.onUserClick = object : AdapterCategories.OnUserClick {
                override fun onClick(position: Int) {
                    deleteCategory(position)
                }
            }
        }
    }

    private fun showAddCategoryDialog() {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            val binding = CustomDialogAddCategoryBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.apply {
                addButton.setOnClickListener {
                    val categoryName = category.text.toString()
                    if (categoryName.isNotEmpty()) {
                        addCategory(categoryName)
                        category.text.clear()
                        dismiss()
                    }
                }
                cancelButton.setOnClickListener {
                    dismiss()
                }
            }
            show()
        }
    }

    private fun addCategory(category : String) {
        val categoryObject = Category(category)
        categoryViewModel.createCategory(categoryObject)
        adapterCategories.addItem(categoryObject)
    }

    private fun deleteCategory(position : Int) {
        val categoryToDelete = adapterCategories.categoriesList[position]
        categoryViewModel.deleteCategory(categoryToDelete)
        adapterCategories.deleteItem(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observers() {
        categoryViewModel.getAllCategoriesLiveData.observe(viewLifecycleOwner) { data ->
            placeholder(data)
            adapterCategories.updateList(data)
        }
    }

    private fun recyclerViewVisibility(arrow : String) {
        binding.apply {
            when (arrow) {
                UP -> {
                    categoriesRecyclerView.visible()
                    arrowUp.gone()
                    arrowDown.visible()
                }
                DOWN -> {
                    categoriesRecyclerView.gone()
                    arrowDown.gone()
                    arrowUp.visible()
                }
            }
        }
    }

    private fun placeholder(list : List<Category>) {
        binding.placeholder.apply {
            if (list.isEmpty()) { visible() }
            else { gone() }
        }
    }
}