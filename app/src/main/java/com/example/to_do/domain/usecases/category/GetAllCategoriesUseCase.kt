package com.example.to_do.domain.usecases.category

import com.example.to_do.domain.model.Category
import com.example.to_do.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke() : List<Category> = categoryRepository.getAllCategories()
}