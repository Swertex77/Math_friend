package com.example.math_friend.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

interface DictRepository {
    fun getWords(): LiveData<List<Theory>>
    suspend fun insert(theory: Theory): Long
    suspend fun update(theory: Theory)
    suspend fun delete(theory: Theory)
    suspend fun deleteAll()

    class Base(context: Context) : DictRepository {
        private val room = Room.databaseBuilder(
            context,
            WordDatabase::class.java,
            DATABASE_NAME
        ).build()
        private val dao = room.wordDAO()
        override fun getWords() = dao.getAllWords()
        override suspend fun insert(theory: Theory) = dao.insertWord(theory)
        override suspend fun update(theory: Theory) = dao.updateWord(theory)
        override suspend fun delete(theory: Theory) = dao.deleteWord(theory)
        override suspend fun deleteAll() = dao.deleteAll()

        companion object {
            private const val DATABASE_NAME = "word_data_database24"
        }
    }
}