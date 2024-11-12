package com.example.to_do.data.repository

import com.example.to_do.data.local.TaskDao
import com.example.to_do.domain.model.Task
import com.example.to_do.domain.repository.TaskRepository

class TaskRepositoryImpl (
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun insertTask(task: Task) =
        taskDao.insertTask(task)

    override suspend fun updateTask(task: Task) =
        taskDao.updateTask(task)

    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(task)

    override fun getAllTasks(): List<Task> =
        taskDao.getAllTasks()

    override fun searchTask(query: String?): List<Task> =
        taskDao.searchTask(query)

    override fun getAllTasksByDate(date: String): List<Task> =
        taskDao.getAllTasksByDate(date)

    override fun getAllTasksByName(taskName: String): List<Task> =
        taskDao.getAllTasksByName(taskName)

    override fun getAllTasksByDescription(taskDescription: String): List<Task> =
        taskDao.getAllTasksByDescription(taskDescription)

    override fun getAllTasksByCategory(category: String): List<Task> =
        taskDao.getAllTasksByCategory(category)

    override fun getAllTasksByStatus(status: Int): List<Task> =
        taskDao.getAllTasksByStatus(status)

}