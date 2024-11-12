package com.example.to_do.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.to_do.domain.model.Task
import com.example.to_do.util.Constants.GET_ALL_TASKS
import com.example.to_do.util.Constants.GET_ALL_TASKS_BY_CATEGORY
import com.example.to_do.util.Constants.GET_ALL_TASKS_BY_DATE
import com.example.to_do.util.Constants.GET_ALL_TASKS_BY_DESCRIPTION
import com.example.to_do.util.Constants.GET_ALL_TASKS_BY_NAME
import com.example.to_do.util.Constants.GET_ALL_TASKS_COMPLETED
import com.example.to_do.util.Constants.SEARCH_TASK

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query (GET_ALL_TASKS)
    fun getAllTasks(): List<Task>

    @Query (SEARCH_TASK)
    fun searchTask(query : String?) : List<Task>

    @Query (GET_ALL_TASKS_BY_DATE)
    fun getAllTasksByDate(date: String) : List<Task>

    @Query (GET_ALL_TASKS_BY_NAME)
    fun getAllTasksByName(taskName: String) : List<Task>

    @Query (GET_ALL_TASKS_BY_DESCRIPTION)
    fun getAllTasksByDescription(taskDescription: String) : List<Task>

    @Query (GET_ALL_TASKS_COMPLETED)
    fun getAllTasksByStatus(taskStatus: Int) : List<Task>

    @Query (GET_ALL_TASKS_BY_CATEGORY)
    fun getAllTasksByCategory(taskCategory: String) : List<Task>
}