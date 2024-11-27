package com.example.to_do.data.repository

import com.example.to_do.data.local.CategoryDao
import com.example.to_do.domain.model.Category
import com.example.to_do.domain.repository.CategoryRepository

class CategoryRepositoryImpl (
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun insertCategory(category: Category) =
        categoryDao.insertCategory(category)

    override suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category)

    override fun getAllCategories(): List<Category> = categoryDao.getAllCategories()
}