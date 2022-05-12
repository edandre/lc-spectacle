package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ExternalUrlsDto(
    @SerializedName("spotify")
    val spotify: String
)