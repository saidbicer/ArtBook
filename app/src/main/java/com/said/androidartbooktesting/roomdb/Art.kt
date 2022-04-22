package com.said.androidartbooktesting.roomdb

import androidx.room.PrimaryKey

data class Art(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl : String
)