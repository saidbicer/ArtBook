package com.said.androidartbooktesting.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.said.androidartbooktesting.data.db.entity.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts")
    fun observerArts() : LiveData<List<Art>>

}