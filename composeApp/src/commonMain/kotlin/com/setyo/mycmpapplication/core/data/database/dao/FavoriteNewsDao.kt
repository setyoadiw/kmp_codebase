package com.setyo.mycmpapplication.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.setyo.mycmpapplication.core.data.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsDao {

    @Upsert
    suspend fun upsert(book: NewsEntity)

    @Query("SELECT * FROM NewsEntity")
    fun getFavoriteBooks(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    suspend fun getFavoriteBook(id: String): NewsEntity?

    @Query("DELETE FROM NewsEntity WHERE id = :id")
    suspend fun deleteFavoriteBook(id: String)
}