package com.example.to_do.presentation.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.domain.model.Category
import com.example.to_do.domain.usecases.category.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor (
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    private var _getAllCategoriesLiveData = MutableLiveData<List<Category>>()
    val getAllCategoriesLiveData : LiveData<List<Category>> get() = _getAllCategoriesLiveData

    fun createCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCases.createCategoryUseCase.invoke(category)
            getAllCategories()
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCases.deleteCategoryUseCase.invoke(category)
            getAllCategories()
        }
    }

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = categoryUseCases.getAllCategoriesUseCase.invoke()
                _getAllCategoriesLiveData.postValue(data)
            } catch (exception : Exception) {
                exception.printStackTrace()
            }
        }
    }

}