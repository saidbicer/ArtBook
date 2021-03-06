package com.said.androidartbooktesting.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl : String
)