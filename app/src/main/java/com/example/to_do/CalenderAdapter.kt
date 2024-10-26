package com.example.to_do

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemDateBinding
import com.google.android.material.textview.MaterialTextView

class CalenderAdapter (
    private val calenderInterface: CalenderInterface,
    private val calenderList: ArrayList<CalenderData>
) : RecyclerView.Adapter<CalenderAdapter.CalenderViewHolder>() {

    var selectItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderViewHolder(binding)
    }

    override fun getItemCount(): Int = calenderList.size

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.bind(calenderList[position], position)
    }

    inner class CalenderViewHolder(val binding: ItemDateBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind (
                calenderData: CalenderData,
                position: Int
            ) {
                binding.apply {
                    val calenderDay = binding.calenderDay
                    val calenderDate = binding.calenderDate
                    val cardView = binding.root

                    if (selectItem == position) {
                        calenderData.isSelected = true
                    }
                    if (calenderData.isSelected) {
                        selectItem = -1
                        calenderDay.setTextColor (
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.white
                            )
                        )
                        calenderDate.setTextColor (
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.white
                            )
                        )
                        cardView.setCardBackgroundColor(
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.blue
                            )
                        )
                    } else {
                        calenderDay.setTextColor (
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.blue
                            )
                        )
                        calenderDate.setTextColor (
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.blue
                            )
                        )
                        cardView.setCardBackgroundColor(
                            ContextCompat.getColor (
                                itemView.context,
                                R.color.white
                            )
                        )
                    }
                    calenderDay.text = calenderData.calenderDay
                    calenderDate.text = calenderData.calenderDate
                    cardView.setOnClickListener {
                        calenderInterface.onSelected(calenderData, adapterPosition, calenderDate)
                    }
                }
            }
    }

    fun setPosition(position: Int) {
        selectItem = position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(calenderList: ArrayList<CalenderData>) {
        this.calenderList.clear()
        this.calenderList.addAll(calenderList)
        notifyDataSetChanged()
    }

    interface CalenderInterface {
        fun onSelected (
            calenderData: CalenderData,
            position: Int,
            day: MaterialTextView
        )
    }
}