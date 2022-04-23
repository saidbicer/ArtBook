package com.said.androidartbooktesting.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.said.androidartbooktesting.data.db.entity.Art
import com.said.androidartbooktesting.data.model.ImageResponse
import com.said.androidartbooktesting.data.repository.ArtRepositoryInterface
import com.said.androidartbooktesting.util.Resource

class FakeArtRepositoryAndroid : ArtRepositoryInterface {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>()


    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0, 0, arrayListOf()))
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}