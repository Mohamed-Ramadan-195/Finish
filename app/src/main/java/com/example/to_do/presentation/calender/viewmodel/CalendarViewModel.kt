package com.example.to_do.presentation.calender.viewmodel

import androidx.lifecycle.LiveData
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
class CalendarViewModel @Inject constructor (
    private val taskUseCases: TaskUseCases
) : ViewModel() {
    private var _getAllTasksByDate = MutableLiveData<List<Task>>()
    val getAllTasksByDate : LiveData<List<Task>> get() = _getAllTasksByDate

    fun getAllTasksByDate(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _getAllTasksByDate.postValue(taskUseCases.getAllTasksByDateUseCase.invoke(date))
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}