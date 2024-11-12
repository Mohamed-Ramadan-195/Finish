package com.example.to_do.di

import android.content.Context
import androidx.room.Room
import com.example.to_do.data.local.TaskDao
import com.example.to_do.data.local.TaskDatabase
import com.example.to_do.data.repository.TaskRepositoryImpl
import com.example.to_do.domain.repository.TaskRepository
import com.example.to_do.domain.usecases.CreateTaskUseCase
import com.example.to_do.domain.usecases.DeleteTaskUseCase
import com.example.to_do.domain.usecases.GetAllTasksUseCase
import com.example.to_do.domain.usecases.SearchTaskUseCase
import com.example.to_do.domain.usecases.TaskUseCases
import com.example.to_do.domain.usecases.UpdateTaskUseCase
import com.example.to_do.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository (
        taskDao: TaskDao
    ) : TaskRepository  = TaskRepositoryImpl(taskDao)

    @Provides
    @Singleton
    fun provideTaskUseCases (
        taskRepository: TaskRepository
    ) : TaskUseCases {
        return TaskUseCases (
            getAllTasksUseCase = GetAllTasksUseCase(taskRepository),
            createTaskUseCase = CreateTaskUseCase(taskRepository),
            deleteTaskUseCase = DeleteTaskUseCase(taskRepository),
            updateTaskUseCase = UpdateTaskUseCase(taskRepository),
            searchTaskUseCase = SearchTaskUseCase(taskRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ) : TaskDatabase {
        return Room.databaseBuilder (
            context = context,
            klass = TaskDatabase::class.java,
            name = DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao (taskDatabase: TaskDatabase) : TaskDao = taskDatabase.taskDao

}