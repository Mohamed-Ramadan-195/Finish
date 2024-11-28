package com.example.to_do.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.to_do.util.Constants.TASKS_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = TASKS_TABLE_NAME,
    foreignKeys = [
        ForeignKey (
            entity = Category::class,
            parentColumns = ["categoryName"],
            childColumns = ["taskCategory"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
@Parcelize
data class Task (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val taskId: Long = 0,
    @ColumnInfo val taskStatus: Boolean,
    @ColumnInfo val taskName: String,
    @ColumnInfo val taskDescription: String,
    @ColumnInfo val taskCategory : String,    // foreign key
    @ColumnInfo val taskDate: String,
    @ColumnInfo val taskTime: String,
) : Parcelable