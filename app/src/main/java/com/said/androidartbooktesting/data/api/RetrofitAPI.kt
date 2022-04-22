package com.said.androidartbooktesting.data.api

import com.said.androidartbooktesting.data.model.ImageResponse
import com.said.androidartbooktesting.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String = Constants.API_KEY
    ) : Response<ImageResponse>

}