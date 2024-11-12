package com.example.to_do.presentation.home.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemTaskBinding
import com.example.to_do.domain.model.Task
import java.util.Random

class AdapterTasks (
    private var tasksList : ArrayList<Task>,
    val onClick : (Task) -> Unit
) : RecyclerView.Adapter<AdapterTasks.ViewHolderTasks>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTasks {
        val itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTasks(itemTaskBinding)
    }

    override fun getItemCount(): Int = tasksList.size

    override fun onBindViewHolder(holder: ViewHolderTasks, position: Int) {
        holder.bind(tasksList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Task>) {
        this.tasksList = list
        notifyDataSetChanged()
    }

    inner class ViewHolderTasks(val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke (tasksList[layoutPosition])
                }
                menu.setOnClickListener {
                    onClick.invoke (tasksList[layoutPosition])
                }
            }
        }

        fun bind(task: Task) {
            binding.apply {
                taskName.text = task.taskName
                taskDate.text = task.taskDate
                taskTime.text = task.taskTime
                taskCategory.text = task.taskCategory
            }
        }

        private fun setColorTask() {
            val random = Random()
            val color = Color.argb (
                225,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
            binding.taskColor.setBackgroundColor(color)
        }
    }
}