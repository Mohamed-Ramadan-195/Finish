package com.example.to_do.domain.repository

import androidx.lifecycle.LiveData
import com.example.to_do.domain.model.Task

interface TaskRepository {
    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTasks() : LiveData<List<Task>>

    fun getAllTasksByDate(date: String) : LiveData<List<Task>>

    fun getAllTasksByName(taskName: String) : LiveData<List<Task>>

    fun getAllTasksByDescription(taskDescription: String) : LiveData<List<Task>>
}