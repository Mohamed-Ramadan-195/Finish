package com.example.to_do.domain.usecases.category

data class CategoryUseCases (
    val createCategoryUseCase: CreateCategoryUseCase,
    val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    val deleteCategoryUseCase: DeleteCategoryUseCase
)
