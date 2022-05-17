package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)