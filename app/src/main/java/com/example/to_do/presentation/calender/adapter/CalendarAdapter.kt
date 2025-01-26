package com.example.to_do.presentation.calender.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemDateBinding
import com.example.to_do.domain.model.CalendarModel

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    var list: ArrayList<CalendarModel> = ArrayList()
    var calendarInterface: CalendarInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) = holder.bind(list[position])

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(calendarList: ArrayList<CalendarModel>) {
        list.clear()
        list.addAll(calendarList)
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(private val binding: ItemDateBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(calendar: CalendarModel) {
            binding.apply {
                // set text
                calenderDay.text = calendar.calendarDay
                calenderDate.text = calendar.calendarDate

                // handle click action
                if (calendar.isSelected) {
                    calenderDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    calenderDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue))
                } else {
                    calenderDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
                    calenderDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
                    card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                }

                // click listener
                card.setOnClickListener {
                    calendarInterface?.onSelect(calendar, adapterPosition)
                }
            }
        }
    }

    interface CalendarInterface {
        fun onSelect(calendar: CalendarModel, position: Int)
    }
}