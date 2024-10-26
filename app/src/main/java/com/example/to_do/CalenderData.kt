package com.example.to_do

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class CalenderData (
    var data : Date,
    var isSelected : Boolean = false
) {
    val calenderDay : String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(data)

    val calenderDate : String
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = data
            return calendar[Calendar.DAY_OF_MONTH].toString()
        }
}