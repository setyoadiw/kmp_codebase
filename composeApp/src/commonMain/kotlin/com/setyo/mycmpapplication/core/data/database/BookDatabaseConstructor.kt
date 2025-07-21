package com.setyo.mycmpapplication.core.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<NewsDatabase> {
    override fun initialize(): NewsDatabase
}