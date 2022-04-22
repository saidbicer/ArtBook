package com.said.androidartbooktesting.model

import com.google.gson.annotations.SerializedName


data class ImageResponse (
    @SerializedName("total") var total: Int? = null,
    @SerializedName("totalHits") var totalHits: Int? = null,
    @SerializedName("hits") var hits: ArrayList<ImageResult> = arrayListOf()
)

data class ImageResult (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("pageURL") var pageURL: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("tags") var tags: String? = null,
    @SerializedName("previewURL") var previewURL: String? = null,
    @SerializedName("previewWidth") var previewWidth: Int? = null,
    @SerializedName("previewHeight") var previewHeight: Int? = null,
    @SerializedName("webformatURL") var webformatURL: String? = null,
    @SerializedName("webformatWidth") var webformatWidth: Int? = null,
    @SerializedName("webformatHeight") var webformatHeight: Int? = null,
    @SerializedName("largeImageURL") var largeImageURL: String? = null,
    @SerializedName("imageWidth") var imageWidth: Int? = null,
    @SerializedName("imageHeight") var imageHeight: Int? = null,
    @SerializedName("imageSize") var imageSize: Int? = null,
    @SerializedName("views") var views: Int? = null,
    @SerializedName("downloads") var downloads: Int? = null,
    @SerializedName("collections") var collections: Int? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("comments") var comments: Int? = null,
    @SerializedName("user_id") var userId: Int? = null,
    @SerializedName("user") var user: String? = null,
    @SerializedName("userImageURL") var userImageURL: String? = null
)