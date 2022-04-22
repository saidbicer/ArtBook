package com.said.androidartbooktesting.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Art::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}