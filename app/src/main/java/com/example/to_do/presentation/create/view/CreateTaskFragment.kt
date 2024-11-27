package com.example.to_do.presentation.create.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCreateTaskBinding
import com.example.to_do.domain.model.Category
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.create.adapters.AdapterCategorySelected
import com.example.to_do.presentation.create.viewmodel.CreateTaskViewModel
import com.example.to_do.presentation.dashboard.viewmodel.CategoryViewModel
import com.example.to_do.util.Constants.DATE_FORMAT
import com.example.to_do.util.Constants.EMPTY_STRING
import com.example.to_do.util.Constants.TIME_FORMAT
import com.example.to_do.util.emptyEditText
import com.example.to_do.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CreateTaskFragment : BaseFragment<FragmentCreateTaskBinding>() {

    private val calendar : Calendar = Calendar.getInstance()
    private val createTaskViewModel : CreateTaskViewModel by viewModels()
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val adapterCategorySelected : AdapterCategorySelected by lazy { AdapterCategorySelected() }
    private var categoryName = EMPTY_STRING

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateTaskBinding {
        return FragmentCreateTaskBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        categoryViewModel.getAllCategories()
        observers()
    }

    override fun onClicks() {
        binding.apply {
            addCategory.setOnClickListener { findNavController().navigate(R.id.action_navigation_create_task_to_dashboardFragment) }
            taskDate.setOnClickListener { selectDatePickerDialog() }
            taskTime.setOnClickListener { selectTimePickerDialog() }
            createTaskButton.setOnClickListener { validate() }
            adapterCategorySelected.onUserClick = object : AdapterCategorySelected.OnUserClick {
                override fun onClick(category: Category) {
                    categoryName = category.categoryName
                }
            }
        }
    }

    private fun observers() {
        categoryViewModel.getAllCategoriesLiveData.observe(viewLifecycleOwner) {
            adapterCategorySelected.categoriesList = it as MutableList<Category>
            binding.categoriesRecyclerView.adapter = adapterCategorySelected
        }
    }

    private fun selectDatePickerDialog() {
        val datePickerDialog = DatePickerDialog (
            requireActivity(), {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                val editableText: Editable = Editable.Factory.getInstance().newEditable(formattedDate)
                binding.taskDate.text = editableText
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.setOnShowListener {
            datePickerDialog.window?.decorView?.setBackgroundColor(requireActivity().getColor(R.color.white))
        }
        datePickerDialog.show()
    }

    @SuppressLint("DefaultLocale")
    private fun selectTimePickerDialog() {
        val hours = Calendar.HOUR
        val minutes = Calendar.MINUTE
        val timePickerDialog = TimePickerDialog (
            requireActivity(), { _, hour, minute ->
                val formattedTime = String.format(TIME_FORMAT, hour, minute)
                val editableText: Editable = Editable.Factory.getInstance().newEditable(formattedTime)
                binding.taskTime.text = editableText
            }, hours, minutes, true
        )
        timePickerDialog.setOnShowListener {
            timePickerDialog.window?.decorView?.setBackgroundColor(requireActivity().getColor(R.color.white))
        }
        timePickerDialog.show()
    }

    private fun validate() {
        binding.apply {
            val name : String = taskName.text.toString()
            val description : String = taskDescription.text.toString()
            val category : String = categoryName
            val date : String = taskDate.text.toString()
            val time : String = taskTime.text.toString()

            if (name.isEmpty()) emptyEditText(taskName)
            else if (description.isEmpty()) emptyEditText(taskDescription)
            else if (category.isEmpty()) showToast(getString(R.string.please_select_task_category))
            else if (date.isEmpty()) showToast(getString(R.string.please_select_task_date))
            else if (time.isEmpty()) showToast(getString(R.string.please_select_task_time))
            else {
                createTaskObject (name, description, category, date, time)
                showToast(getString(R.string.created_task_successfully))
                clearText()
            }
        }
    }

    private fun createTaskObject (
        name : String,
        description : String,
        category : String,
        date : String,
        time : String
    ) {
        val task = Task (
            taskName = name,
            taskDescription = description,
            taskCategory = category,
            taskDate = date,
            taskTime = time
        )
        createTaskViewModel.createTask(task)
    }

    private fun clearText() {
        binding.apply {
            taskName.text?.clear()
            taskDescription.text?.clear()
            taskDate.text?.clear()
            taskTime.text?.clear()
        }
    }

}