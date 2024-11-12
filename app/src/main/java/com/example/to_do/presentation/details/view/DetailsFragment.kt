package com.example.to_do.presentation.details.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentDetailsBinding
import com.example.to_do.util.Constants.TASK_CATEGORY
import com.example.to_do.util.Constants.TASK_DATE
import com.example.to_do.util.Constants.TASK_DESCRIPTION
import com.example.to_do.util.Constants.TASK_NAME
import com.example.to_do.util.Constants.TASK_TIME
import com.example.to_do.util.showToast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {
            taskNameEdit.setOnClickListener { showUpdateDialog(TASK_NAME) }
            taskDescriptionEdit.setOnClickListener { showUpdateDialog(TASK_DESCRIPTION) }
            taskCategoryEdit.setOnClickListener { showUpdateDialog(TASK_CATEGORY) }
            taskDateEdit.setOnClickListener { showUpdateDialog(TASK_DATE) }
            taskTimeEdit.setOnClickListener { showUpdateDialog(TASK_TIME) }
            deleteButton.setOnClickListener {  }
            updateButton.setOnClickListener {  }
        }
    }

    private fun showUpdateDialog(update : String) {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            setContentView(R.layout.custom_dialog_update)
        }
        val updateText : MaterialTextView = dialog.findViewById(R.id.updateText)
        val updateButton : MaterialButton = dialog.findViewById(R.id.updateButton)
        val cancelButton : MaterialButton = dialog.findViewById(R.id.cancelButton)
        val updatedText = updateText.text.toString()
        updateButton.setOnClickListener {
            if (updatedText.isNotEmpty()) {
                binding.apply {
                    when (update) {
                        TASK_NAME -> taskName.text = updatedText
                        TASK_DESCRIPTION -> taskDescription.text = updatedText
                        TASK_CATEGORY -> taskCategory.text = updatedText
                        TASK_DATE -> taskDate.text = updatedText
                        TASK_TIME -> taskTime.text = updatedText
                    }
                }
            } else showToast(getString(R.string.please_enter_your_update))
            dialog.dismiss()
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}