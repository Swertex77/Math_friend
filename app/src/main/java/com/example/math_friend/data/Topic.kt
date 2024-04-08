package com.example.math_friend.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theory_data_table")
data class Topic(
    @PrimaryKey
    val classId: Int,
    val topicId: Int,
    val courseId: Int
)