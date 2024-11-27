package com.example.to_do.presentation.create.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.domain.model.Task
import com.example.to_do.domain.usecases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor (
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    fun createTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCases.createTaskUseCase.invoke(task)
        }
    }

}