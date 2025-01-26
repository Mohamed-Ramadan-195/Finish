package com.example.to_do.domain.model

import com.example.to_do.util.Constants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.util.Locale

data class CalendarModel (
    var date: Date,
    var isSelected: Boolean = false
) {
    val calendarDay: String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(date)

    val calendarDate: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar[Calendar.DAY_OF_MONTH].toString()
        }

    val completeDate: String
        get() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date)
}
