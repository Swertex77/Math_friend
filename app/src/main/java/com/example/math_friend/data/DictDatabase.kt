package com.example.math_friend.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Theory::class], version = 1)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDAO(): MaterialDAO
}