package com.example.to_do.presentation.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.to_do.data.repository.TaskRepositoryImpl

@Suppress("UNCHECKED_CAST")
class TaskViewModelFactory (
    private val application: Application,
    private val taskRepositoryImpl: TaskRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(application, taskRepositoryImpl) as T
    }

}