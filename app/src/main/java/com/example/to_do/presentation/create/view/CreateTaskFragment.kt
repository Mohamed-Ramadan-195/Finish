package com.example.to_do.presentation.create.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.to_do.R
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCreateTaskBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.create.viewmodel.CreateTaskViewModel
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

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateTaskBinding {
        return FragmentCreateTaskBinding.inflate(inflater, container, false)
    }

    override fun initialize() {  }

    override fun onClicks() {
        binding.apply {
            taskDate.setOnClickListener { selectDatePickerDialog() }
            taskTime.setOnClickListener { selectTimePickerDialog() }
            createTaskButton.setOnClickListener { validate() }
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
            val category : String = categorySpinner.text.toString()
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
            taskId = 0,
            taskStatus = 0,
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
            taskName.setText(EMPTY_STRING)
            taskDescription.setText(EMPTY_STRING)
            categorySpinner.setText(EMPTY_STRING)
            taskDate.setText(EMPTY_STRING)
            taskTime.setText(EMPTY_STRING)
        }
    }

}