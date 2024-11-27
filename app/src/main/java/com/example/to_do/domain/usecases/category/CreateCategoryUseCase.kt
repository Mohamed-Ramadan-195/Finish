package com.example.to_do.domain.usecases.category

import com.example.to_do.domain.model.Category
import com.example.to_do.domain.repository.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(category: Category) {
        categoryRepository.insertCategory(category)
    }

}