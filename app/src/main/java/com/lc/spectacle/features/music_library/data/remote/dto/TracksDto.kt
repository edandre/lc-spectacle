package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TracksDto(
    @SerializedName("href")
    val href: String,
    @SerializedName("items")
    val items: List<TrackDto>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total")
    val total: Int
)