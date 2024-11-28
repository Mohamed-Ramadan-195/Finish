package com.example.to_do.presentation.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.domain.model.Task
import com.example.to_do.domain.usecases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val tasksUseCases: TaskUseCases
) : ViewModel() {

    private var _getTaskById = MutableLiveData<Task>()
    val getTaskById get() = _getTaskById

    fun getTaskById(id : Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _getTaskById.postValue(tasksUseCases.getTaskById.invoke(id))
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            tasksUseCases.updateTaskUseCase.invoke(task)
        }
    }
}