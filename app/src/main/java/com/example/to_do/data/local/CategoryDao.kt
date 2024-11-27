package com.example.to_do.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.to_do.domain.model.Category
import com.example.to_do.util.Constants.GET_ALL_CATEGORIES

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query(GET_ALL_CATEGORIES)
    fun getAllCategories() : List<Category>

}