package com.said.androidartbooktesting.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.said.androidartbooktesting.data.db.entity.Art
import com.said.androidartbooktesting.data.db.dao.ArtDao

@Database(entities = [Art::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}