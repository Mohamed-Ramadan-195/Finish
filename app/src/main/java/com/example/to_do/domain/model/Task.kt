package com.example.to_do.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_do.util.Constants.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class Task (
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskStatus: Int,
    val taskName: String,
    val taskDescription: String,
    val taskCategory: String,
    val taskDate: String,
    val taskTime: String,
) : Parcelable