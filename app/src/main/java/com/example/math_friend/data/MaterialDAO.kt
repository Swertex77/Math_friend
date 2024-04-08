package com.example.math_friend.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
//import

@Dao
interface MaterialDAO {
    @Insert
    suspend fun insertWord(theory: Theory): Long

    @Update
    suspend fun updateWord(theory: Theory)

    @Delete
    suspend fun deleteWord(theory: Theory)

    @Query("SELECT * FROM Theory WHERE topicId =:topicId")
    fun getTheoryForTopic(topicId:Int): LiveData<List<Theory>>

    @Query("SELECT * FROM Practice WHERE topicId =:topicId")
    fun getPracticeForTopic(topicId:Int): LiveData<List<Practice>>
}