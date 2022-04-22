package com.said.androidartbooktesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.said.androidartbooktesting.model.ImageResponse
import com.said.androidartbooktesting.repo.ArtRepositoryInterface
import com.said.androidartbooktesting.roomdb.Art
import com.said.androidartbooktesting.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repository: ArtRepositoryInterface) :
    ViewModel() {

    //ART FRAGMENT
    val artList = repository.getArt()

    //IMAGE API FRAGMENT
    private val _imageList = MutableLiveData<Resource<ImageResponse>>()
    val imageList: LiveData<Resource<ImageResponse>>
        get() = _imageList

    private val _selectenImageUrl = MutableLiveData<String>()
    val selectenImageUrl: LiveData<String>
        get() = _selectenImageUrl

    //ART DETAILS FRAGMENT
    private var _insertArtMessage = MutableLiveData<Resource<Art>>()
    val insertArtMessage: LiveData<Resource<Art>>
        get() = _insertArtMessage

    fun resertInsertArtMsg() {
        _insertArtMessage = MutableLiveData<Resource<Art>>()
    }

    fun selectedImage(url: String) {
        _selectenImageUrl.postValue(url)
    }

    fun setSelectedImage(url: String) = viewModelScope.launch {
        _selectenImageUrl.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch {
        repository.deleteArt(art)
    }

    private fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            _insertArtMessage.postValue(Resource.error("Enter name, artist or year", null))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            _insertArtMessage.postValue(Resource.error("Yeal should be number", null))
            return
        }

        val art = Art(null, name, artistName, yearInt, _selectenImageUrl.value ?: "")
        insertArt(art)
        setSelectedImage("")
        _insertArtMessage.postValue(Resource.success(art))
    }

    fun searchForImage(searchString: String) {
        if (searchString.isEmpty()) {
            return
        }

        _imageList.value = Resource.loading(null)

        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            _imageList.value = response
        }
    }


}