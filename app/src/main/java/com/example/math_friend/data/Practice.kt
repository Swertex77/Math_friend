package com.example.math_friend.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theory_data_table")
data class Practice(
    @PrimaryKey
    val practiceId: Int,
    val topicId: Int,
    val content: String
)
@ColumnInfo(name = "word_id")
var id: Int,

@ColumnInfo(name = "word_name")
var word: String,

@ColumnInfo(name = "word_translate")
var translate: String,

