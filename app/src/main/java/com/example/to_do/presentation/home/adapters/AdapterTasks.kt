package com.example.to_do.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.R
import com.example.to_do.databinding.ItemTaskBinding
import com.example.to_do.domain.model.Task
import com.example.to_do.util.Constants
import com.example.to_do.util.Constants.BROWN_COLOR
import com.example.to_do.util.Constants.COMPLETE
import com.example.to_do.util.Constants.DELETE
import com.example.to_do.util.Constants.GREEN_COLOR
import com.example.to_do.util.Constants.MINT_GREEN_COLOR
import com.example.to_do.util.Constants.ORANGE_COLOR
import com.example.to_do.util.Constants.PINK_COLOR
import com.example.to_do.util.Constants.PURPLE_COLOR
import com.example.to_do.util.Constants.RED_COLOR
import com.example.to_do.util.Constants.SALMON_COLOR
import com.example.to_do.util.Constants.UPDATE
import com.example.to_do.util.Constants.YELLOW_COLOR
import com.google.android.material.imageview.ShapeableImageView
import java.util.ArrayList

class AdapterTasks (val onClick: (Task, String) -> Unit)
    : RecyclerView.Adapter<AdapterTasks.ViewHolderTasks>() {

    private val differCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.taskId == newItem.taskId &&
                   oldItem.taskName == newItem.taskName &&
                   oldItem.taskDescription == newItem.taskDescription &&
                   oldItem.taskCategory == newItem.taskCategory &&
                   oldItem.taskDate == newItem.taskDate &&
                   oldItem.taskTime == newItem.taskTime
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTasks {
        val itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderTasks(itemTaskBinding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolderTasks, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class ViewHolderTasks(val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    onClick.invoke(differ.currentList[layoutPosition], COMPLETE)
                }
                deleteButton.setOnClickListener {
                    onClick.invoke(differ.currentList[layoutPosition], DELETE)
                }
                completeButton.setOnClickListener {
                    onClick.invoke(differ.currentList[layoutPosition], UPDATE)
                }
            }
        }

        fun bind(task: Task) {
            binding.apply {
                taskDate.text = task.taskDate
                taskTime.text = task.taskTime
                taskName.text = task.taskName
                taskDescription.text = task.taskDescription
            }
        }

        private fun setCategoryColor (
            color: String,
            shapeableImageView: ShapeableImageView
        ) {
            binding.apply {
                shapeableImageView.setBackgroundColor (
                    when (color) {
                        YELLOW_COLOR -> R.color.yellow
                        GREEN_COLOR -> R.color.green
                        MINT_GREEN_COLOR -> R.color.mint_green
                        ORANGE_COLOR -> R.color.orange
                        PURPLE_COLOR -> R.color.purple
                        BROWN_COLOR -> R.color.brown
                        RED_COLOR -> R.color.red
                        PINK_COLOR -> R.color.pink
                        SALMON_COLOR -> R.color.salmon
                        else -> R.color.blue
                    }
                )
            }
        }
    }
}