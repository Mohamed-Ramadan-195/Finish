package com.example.to_do.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemTaskBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.util.Constants.COMPLETE
import com.example.to_do.util.Constants.DELETE
import com.example.to_do.util.Constants.UPDATE

class AdapterTasks : RecyclerView.Adapter<AdapterTasks.ViewHolderTasks>() {

    var tasksList : ArrayList<Task> = ArrayList()
    var onUserClick : OnUserClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTasks {
        val itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTasks(itemTaskBinding)
    }

    override fun getItemCount(): Int = tasksList.size

    override fun onBindViewHolder(holder: ViewHolderTasks, position: Int) {
        holder.apply {
            bind(tasksList[position])
            binding.menu.setOnClickListener { view ->
                createTaskPopMenu(view, position)
            }
        }
    }

    inner class ViewHolderTasks(val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onUserClick?.onClick(tasksList[layoutPosition], UPDATE)
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
    }

    private fun createTaskPopMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.apply {
            inflate(R.menu.task_options_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.update -> {
                        onUserClick?.onClick(tasksList[position], UPDATE)
                        true
                    }
                    R.id.delete -> {
                        onUserClick?.onClick(tasksList[position], DELETE)
                        true
                    }
                    R.id.complete -> {
                        onUserClick?.onClick(tasksList[position], COMPLETE)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            show()
        }
    }

    interface OnUserClick {
        fun onClick(task : Task, action : String)
    }
}