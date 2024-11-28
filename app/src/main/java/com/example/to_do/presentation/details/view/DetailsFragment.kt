package com.example.to_do.presentation.details.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.CustomDialogUpdateBinding
import com.example.to_do.databinding.FragmentDetailsBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.details.viewmodel.DetailsViewModel
import com.example.to_do.util.Constants.TASK_CATEGORY
import com.example.to_do.util.Constants.TASK_DATE
import com.example.to_do.util.Constants.TASK_DESCRIPTION
import com.example.to_do.util.Constants.TASK_NAME
import com.example.to_do.util.Constants.TASK_TIME
import com.example.to_do.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val detailsViewModel : DetailsViewModel by viewModels()
    private var id : Long = 0

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        getTaskDetails()
    }

    private fun getTaskDetails() {
        id = DetailsFragmentArgs.fromBundle(requireArguments()).taskId
        detailsViewModel.getTaskById(id)
        attachTaskDetails()
    }

    private fun attachTaskDetails() {
        detailsViewModel.getTaskById.observe(viewLifecycleOwner) { task ->
            binding.apply {
                taskName.text = task.taskName
                taskDescription.text = task.taskDescription
                taskCategory.text = task.taskCategory
                taskDate.text = task.taskDate
                taskTime.text = task.taskTime
            }
        }
    }

    override fun onClicks() {
        binding.apply {
            taskNameEdit.setOnClickListener {
                showUpdateDialog(TASK_NAME)
            }
            taskDescriptionEdit.setOnClickListener {
                showUpdateDialog(TASK_DESCRIPTION)
            }
            taskCategoryEdit.setOnClickListener {
                showUpdateDialog(TASK_CATEGORY)
            }
            taskDateEdit.setOnClickListener {
                showUpdateDialog(TASK_DATE)
            }
            taskTimeEdit.setOnClickListener {
                showUpdateDialog(TASK_TIME)
            }
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            updateButton.setOnClickListener {
                getUpdates()
            }
        }
    }

    private fun showUpdateDialog(update : String) {
        val dialog = Dialog(requireActivity())
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            val updateBinding = CustomDialogUpdateBinding.inflate(layoutInflater)
            setContentView(updateBinding.root)
            updateBinding.apply {
                updateButton.setOnClickListener {
                    val updatedText = updateBinding.updateText.text.toString()
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
                    } else {
                        showToast(getString(R.string.please_enter_your_update))
                    }
                    dismiss()
                }
                cancelButton.setOnClickListener { dismiss() }
            }
            show()
        }
    }

    private fun getUpdates () {
        binding.apply {
            val name = taskName.text.toString()
            val description = taskDescription.text.toString()
            val category = taskCategory.text.toString()
            val date = taskDate.text.toString()
            val time = taskName.text.toString()

            updateTask(name, description, category, date, time)
        }
    }

    private fun updateTask (
        name : String,
        description : String,
        category : String,
        date : String,
        time : String
    ) {
        val task = Task (
            taskName = name,
            taskDescription = description,
            taskStatus = false,
            taskCategory = category,
            taskDate = date,
            taskTime = time
        )
        detailsViewModel.updateTask(task)
    }
}