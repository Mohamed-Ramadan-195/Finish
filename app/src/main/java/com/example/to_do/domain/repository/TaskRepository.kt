package com.example.to_do.domain.repository

import com.example.to_do.domain.model.Task

interface TaskRepository {
    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTasks() : List<Task>

    fun searchTask(query : String?) : List<Task>

    fun getAllTasksByDate(date: String) : List<Task>

    fun getAllTasksByName(taskName: String) : List<Task>

    fun getAllTasksByDescription(taskDescription: String) : List<Task>

    fun getAllTasksByCategory(category: String): List<Task>

    fun getAllTasksByStatus(status: Boolean): List<Task>

    fun getTaskById(id : Long) : Task
}