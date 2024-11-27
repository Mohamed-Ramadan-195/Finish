package com.example.to_do.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.to_do.domain.model.Category
import com.example.to_do.domain.model.Task

@Database(entities = [Task::class, Category::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val taskDao : TaskDao
    abstract val categoryDao : CategoryDao

}