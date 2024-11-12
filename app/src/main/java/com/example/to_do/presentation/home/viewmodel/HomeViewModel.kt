package com.example.to_do.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.domain.model.Task
import com.example.to_do.domain.usecases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private var _getAllTasksLiveData = MutableLiveData<List<Task>>()
    val getAllTasksLiveData get() = _getAllTasksLiveData

    fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getAllTasksLiveData.postValue(taskUseCases.getAllTasksUseCase.invoke())
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskUseCases.deleteTaskUseCase.invoke(task)
        }
    }

}