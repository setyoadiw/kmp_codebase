package com.setyo.mycmpapplication.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.setyo.mycmpapplication.core.data.database.converter.StringListTypeConverter
import com.setyo.mycmpapplication.core.data.database.dao.FavoriteNewsDao
import com.setyo.mycmpapplication.core.data.database.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract val favoriteNews: FavoriteNewsDao

    companion object {
        const val DB_NAME = "news.db"
    }
}