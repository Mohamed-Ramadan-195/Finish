package com.example.to_do.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to_do.domain.model.Task
import com.example.to_do.util.Constants.DATABASE_NAME

@Database(entities = [Task::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao() : TaskDao

    companion object {
        @Volatile
        private var instance: TaskDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance ?:
            createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder (
                    context.applicationContext,
                    TaskDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
    }
}