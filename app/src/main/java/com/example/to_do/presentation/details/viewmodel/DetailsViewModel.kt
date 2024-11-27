package com.example.to_do.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.to_do.domain.usecases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val tasksUseCases: TaskUseCases
) : ViewModel() {



}