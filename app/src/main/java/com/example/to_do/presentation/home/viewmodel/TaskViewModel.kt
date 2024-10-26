package com.example.to_do.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.data.repository.TaskRepositoryImpl
import com.example.to_do.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (
    application: Application,
    private val taskRepositoryImpl: TaskRepositoryImpl
) : AndroidViewModel(application) {

    fun insertTask (task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepositoryImpl.insertTask(task)
        }
    }

    fun updateTask (task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepositoryImpl.updateTask(task)
        }
    }

    fun deleteTask (task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepositoryImpl.deleteTask(task)
        }
    }

    fun getAllTasks() = taskRepositoryImpl.getAllTasks()

    fun getAllTasksByDate(taskDate: String) =
        taskRepositoryImpl.getAllTasksByDate(taskDate)

    fun getAllTasksByName(taskName: String) =
        taskRepositoryImpl.getAllTasksByName(taskName)

    fun getAllTasksByDescription(taskDescription: String) =
        taskRepositoryImpl.getAllTasksByDescription(taskDescription)

}