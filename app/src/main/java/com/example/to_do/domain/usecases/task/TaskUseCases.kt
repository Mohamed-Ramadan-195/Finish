package com.example.to_do.domain.usecases.task

data class TaskUseCases (
    val getAllTasksUseCase : GetAllTasksUseCase,
    val createTaskUseCase: CreateTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val searchTaskUseCase: SearchTaskUseCase
)