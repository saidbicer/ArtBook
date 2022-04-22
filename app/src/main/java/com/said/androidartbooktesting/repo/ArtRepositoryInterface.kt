package com.said.androidartbooktesting.repo

import androidx.lifecycle.LiveData
import com.said.androidartbooktesting.model.ImageResponse
import com.said.androidartbooktesting.roomdb.Art
import com.said.androidartbooktesting.utils.Resource

interface ArtRepositoryInterface {
    suspend fun insertArt(art: Art)
    suspend fun deleteArt(art: Art)
    fun getArt(): LiveData<List<Art>>
    suspend fun searchImage(imageString: String): Resource<ImageResponse>
}