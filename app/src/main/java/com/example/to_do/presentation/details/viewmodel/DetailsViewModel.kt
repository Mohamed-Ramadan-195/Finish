package com.example.to_do.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.domain.model.Task
import com.example.to_do.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val tasksUseCases: TaskUseCases
) : ViewModel() {

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            tasksUseCases.deleteTaskUseCase.invoke(task)
        }
    }

}