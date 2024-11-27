package com.example.to_do.domain.repository

import com.example.to_do.domain.model.Category

interface CategoryRepository {

    suspend fun insertCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getAllCategories() : List<Category>

}