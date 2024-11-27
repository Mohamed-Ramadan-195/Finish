package com.example.to_do.domain.usecases.task

import com.example.to_do.domain.model.Task
import com.example.to_do.domain.repository.TaskRepository
import javax.inject.Inject

class SearchTaskUseCase @Inject constructor (
    private val taskRepository: TaskRepository
) {

    operator fun invoke (query : String?) : List<Task> =
        taskRepository.searchTask(query)

}