package com.said.androidartbooktesting.data.repository

import androidx.lifecycle.LiveData
import com.said.androidartbooktesting.data.model.ImageResponse
import com.said.androidartbooktesting.data.db.entity.Art
import com.said.androidartbooktesting.util.Resource

interface ArtRepositoryInterface {
    suspend fun insertArt(art: Art)
    suspend fun deleteArt(art: Art)
    fun getArt(): LiveData<List<Art>>
    suspend fun searchImage(imageString: String): Resource<ImageResponse>
}