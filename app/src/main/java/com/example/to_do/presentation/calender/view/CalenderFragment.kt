package com.example.to_do.presentation.calender.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.to_do.base.BaseFragment
import com.example.to_do.databinding.FragmentCalenderBinding
import com.example.to_do.domain.model.CalendarModel
import com.example.to_do.domain.model.Task
import com.example.to_do.presentation.calender.adapter.CalendarAdapter
import com.example.to_do.presentation.calender.viewmodel.CalendarViewModel
import com.example.to_do.presentation.home.adapters.AdapterTasks
import com.example.to_do.util.placeholder
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class CalenderFragment : BaseFragment<FragmentCalenderBinding>() {

    private val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private val calendarList = ArrayList<CalendarModel>()
    private val calendarAdapter: CalendarAdapter by lazy { CalendarAdapter() }
    private val adapterTasks: AdapterTasks by lazy { AdapterTasks() }
    private val calendarViewModel: CalendarViewModel by viewModels()

    override fun getViewBinding (
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalenderBinding {
        return FragmentCalenderBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        init()
        getDates()
        getTasksByDate()
    }

    override fun onClicks() {
        calendarAdapter.calendarInterface = object : CalendarAdapter.CalendarInterface {
            override fun onSelect (
                calendar: CalendarModel,
                position: Int,
            ) {
                calendarList.forEachIndexed { index, calendarModel ->
                    calendarModel.isSelected = index == position
                    calendarViewModel.getAllTasksByDate(calendar.completeDate)
                }
                calendarAdapter.updateList(calendarList)
            }
        }
        binding.apply {
            monthYear.setOnClickListener {
                displayDatePicker()
            }
        }
    }

    private fun init() {
        binding.apply {
            monthYear.text = simpleDateFormat.format(calendar.time)
            calendarRecyclerView.setHasFixedSize(true)
            calendarRecyclerView.adapter = calendarAdapter
        }
    }

    private fun displayDatePicker() {
        val materialDateBuilder: MaterialDatePicker.Builder<Long> = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("Select Date")
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.addOnPositiveButtonClickListener {
            try {
                binding.monthYear.text = simpleDateFormat.format(it)
                calendar.time = Date(it)
                getDates()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }

    private fun getDates() {
        val dateList = ArrayList<CalendarModel>()
        val dates = ArrayList<Date>()
        val monthCalendar = calendar.clone() as Calendar
        val maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            dateList.add(CalendarModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList.clear()
        calendarList.addAll(dateList)
        calendarAdapter.updateList(dateList)
    }

    private fun getTasksByDate() {
        calendarViewModel.getAllTasksByDate.observe(viewLifecycleOwner) {
            val data = it as ArrayList<Task>
            adapterTasks.tasksList = data
            binding.tasksRecyclerView.adapter = adapterTasks
            placeholder(data, binding.placeholderLayout)
        }
    }

}