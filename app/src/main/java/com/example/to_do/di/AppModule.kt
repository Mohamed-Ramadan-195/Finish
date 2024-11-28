package com.example.to_do.di

import android.content.Context
import androidx.room.Room
import com.example.to_do.data.local.TaskDao
import com.example.to_do.data.local.AppDatabase
import com.example.to_do.data.local.CategoryDao
import com.example.to_do.data.repository.CategoryRepositoryImpl
import com.example.to_do.data.repository.TaskRepositoryImpl
import com.example.to_do.domain.repository.CategoryRepository
import com.example.to_do.domain.repository.TaskRepository
import com.example.to_do.domain.usecases.category.CategoryUseCases
import com.example.to_do.domain.usecases.category.CreateCategoryUseCase
import com.example.to_do.domain.usecases.category.DeleteCategoryUseCase
import com.example.to_do.domain.usecases.category.GetAllCategoriesUseCase
import com.example.to_do.domain.usecases.task.CreateTaskUseCase
import com.example.to_do.domain.usecases.task.DeleteTaskUseCase
import com.example.to_do.domain.usecases.task.GetAllTasksUseCase
import com.example.to_do.domain.usecases.task.GetTaskById
import com.example.to_do.domain.usecases.task.SearchTaskUseCase
import com.example.to_do.domain.usecases.task.TaskUseCases
import com.example.to_do.domain.usecases.task.UpdateTaskUseCase
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
            searchTaskUseCase = SearchTaskUseCase(taskRepository),
            getTaskById = GetTaskById(taskRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTaskDao (appDatabase: AppDatabase) : TaskDao = appDatabase.taskDao

    @Provides
    @Singleton
    fun provideCategoryRepository (
        categoryDao: CategoryDao
    ) : CategoryRepository  = CategoryRepositoryImpl(categoryDao)

    @Provides
    @Singleton
    fun provideCategoryUseCases (
        categoryRepository: CategoryRepository
    ) : CategoryUseCases {
        return CategoryUseCases (
            createCategoryUseCase = CreateCategoryUseCase(categoryRepository),
            getAllCategoriesUseCase = GetAllCategoriesUseCase(categoryRepository),
            deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)
        )
    }

    @Provides
    @Singleton
    fun provideCategoryDao (appDatabase: AppDatabase) : CategoryDao = appDatabase.categoryDao

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder (
            context = context,
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}