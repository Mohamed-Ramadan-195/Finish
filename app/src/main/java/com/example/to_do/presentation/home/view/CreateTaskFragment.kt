package com.example.to_do.presentation.home.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCreateTaskBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.home.viewmodel.TaskViewModel
import com.example.to_do.util.Constants.DATE_FORMAT
import com.example.to_do.util.Constants.TIME_FORMAT
import com.example.to_do.util.emptyEditText
import com.example.to_do.util.showToast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateTaskFragment : BaseFragment<FragmentCreateTaskBinding>() {

    private lateinit var taskViewModel : TaskViewModel
    private val calendar : Calendar = Calendar.getInstance()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateTaskBinding {
        return FragmentCreateTaskBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
    }

    override fun onClicks() {
        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }
            dateIcon.setOnClickListener { selectDatePickerDialog() }
            timeIcon.setOnClickListener { selectTimePickerDialog() }
            createTaskButton.setOnClickListener { validate() }
        }
    }

    private fun validate() {
        binding.apply {
            val name : String = taskName.text.toString()
            val description : String = taskDescription.text.toString()
            val category : String = categorySpinner.text.toString()
            val date : String = taskDate.text.toString()
            val time : String = taskTime.text.toString()

            if (name.isEmpty()) emptyEditText(taskName)
            else if (description.isEmpty()) emptyEditText(taskDescription)
            else if (category.isEmpty()) showToast(getString(R.string.please_select_task_category))
            else if (date.isEmpty()) showToast(getString(R.string.please_select_task_date))
            else if (time.isEmpty()) showToast(getString(R.string.please_select_task_time))
            else {
                val task = Task(
                    taskId = 0,
                    taskStatus = 0,
                    taskName = name,
                    taskDescription = description,
                    taskCategory = category,
                    taskDate = date,
                    taskTime = time
                )
                taskViewModel.insertTask(task)
                showToast(getString(R.string.created_task_successfully))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setSpinner()
    }

    @SuppressLint("ResourceAsColor")
    private fun setSpinner() {
        val categories = resources.getStringArray(R.array.Categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, categories)
        binding.categorySpinner.apply {
            setDropDownBackgroundResource(R.color.white)
            setAdapter(arrayAdapter)
        }
    }

    private fun selectDatePickerDialog() {
        val datePickerDialog = DatePickerDialog (
            requireActivity(), {_, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.taskDate.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    @SuppressLint("DefaultLocale")
    private fun selectTimePickerDialog() {
        val hours = Calendar.HOUR
        val minutes = Calendar.MINUTE
        val timePickerDialog = TimePickerDialog (
            requireActivity(), { _, hour, minute ->
                val formattedTime = String.format(TIME_FORMAT, hour, minute)
                binding.taskTime.text = formattedTime
            }, hours, minutes, true
        )
        timePickerDialog.show()
    }
}