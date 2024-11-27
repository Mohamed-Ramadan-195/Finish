package com.example.to_do.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_do.util.Constants.CATEGORIES_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = CATEGORIES_TABLE_NAME)
@Parcelize
data class Category (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo val categoryName: String
) : Parcelable