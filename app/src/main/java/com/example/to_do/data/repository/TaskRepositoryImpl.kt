package com.example.to_do.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.to_do.data.local.TaskDatabase
import com.example.to_do.domain.model.Task

class TaskRepositoryImpl (
    private val taskDatabase: TaskDatabase
) {
    suspend fun insertTask(task: Task) = taskDatabase.getTaskDao().insertTask(task)

    suspend fun updateTask(task: Task) = taskDatabase.getTaskDao().updateTask(task)

    suspend fun deleteTask(task: Task) = taskDatabase.getTaskDao().deleteTask(task)

    fun getAllTasks(): LiveData<List<Task>>
        = taskDatabase.getTaskDao().getAllTasks()

    fun getAllTasksByDate(date: String): LiveData<List<Task>>
        = taskDatabase.getTaskDao().getAllTasksByDate(date)

    fun getAllTasksByName(taskName: String): LiveData<List<Task>>
        = taskDatabase.getTaskDao().getAllTasksByName(taskName)

    fun getAllTasksByDescription(taskDescription: String): LiveData<List<Task>>
        = taskDatabase.getTaskDao().getAllTasksByDescription(taskDescription)
}