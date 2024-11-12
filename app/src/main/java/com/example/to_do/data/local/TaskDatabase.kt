package com.example.to_do.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.to_do.domain.model.Task

@Database(entities = [Task::class], version = 3)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao : TaskDao

}