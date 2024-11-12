package com.example.to_do.presentation.dashboard.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentDashboardBinding
import com.example.to_do.presentation.dashboard.adapters.AdapterCategories
import com.example.to_do.util.Constants.EMPTY_STRING
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    private val categoriesList = ArrayList<String>()
    private lateinit var adapterCategories: AdapterCategories

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {
            addCategory.setOnClickListener {
                showAddCategoryDialog()
            }
        }
    }

    private fun showAddCategoryDialog() {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setContentView(R.layout.custom_dialog_add_category)

            val categoryName : MaterialTextView = dialog.findViewById(R.id.categoryName)
            val cancelButton : MaterialButton = dialog.findViewById(R.id.cancelButton)
            val addButton : MaterialButton = dialog.findViewById(R.id.addButton)

            val categoryNameText : String = categoryName.text.toString()
            addButton.setOnClickListener {
                addCategory(categoryNameText)
                categoryName.text = EMPTY_STRING
                dismiss()
            }
            cancelButton.setOnClickListener { dismiss() }
            show()
        }
    }

    private fun addCategory(category : String) {
        if (category.isNotEmpty()) {
            categoriesList.add(category)
            adapterCategories.notifyItemInserted(categoriesList.size - 1)
            binding.apply {
                categoriesRecyclerView.scrollToPosition(categoriesList.size - 1)
            }
        }
    }
}