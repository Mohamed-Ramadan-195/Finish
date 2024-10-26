package com.example.to_do.util

object Constants {
    const val SPLASH_TIME = 3000

    // Category Colors
    const val YELLOW_COLOR = "yellow"
    const val GREEN_COLOR = "green"
    const val MINT_GREEN_COLOR = "mint_green"
    const val ORANGE_COLOR = "orange"
    const val PURPLE_COLOR = "purple"
    const val BROWN_COLOR = "brown"
    const val RED_COLOR = "red"
    const val PINK_COLOR = "pink"
    const val SALMON_COLOR = "salmon"

    // Visibility
    const val VISIBLE = "visible"
    const val GONE = "gone"

    // Database
    const val TABLE_NAME = "Tasks"
    const val DATABASE_NAME = "Tasks Database"

    // Queries
    const val GET_ALL_TASKS = "SELECT * FROM Tasks ORDER BY taskId DESC"
    const val GET_ALL_TASKS_BY_DATE = "SELECT * FROM TASKS WHERE taskDate LIKE :date"
    const val GET_ALL_TASKS_BY_NAME = "SELECT * FROM TASKS WHERE taskName LIKE :taskName"
    const val GET_ALL_TASKS_BY_DESCRIPTION = "SELECT * FROM TASKS WHERE taskDescription LIKE :taskDescription"
    const val GET_ALL_TASKS_COMPLETED = "SELECT * FROM TASKS WHERE taskStatus LIKE :taskStatus"
    const val GET_ALL_TASKS_BY_CATEGORY = "SELECT * FROM TASKS WHERE taskCategory LIKE :taskCategory"

    // Calender
    const val DATE_FORMAT = "dd-MM-yyyy"
    const val TIME_FORMAT = "%02d:%02d"

    // Operations
    const val DELETE = "Delete"
    const val COMPLETE = "Complete"
    const val UPDATE = "Update"
}