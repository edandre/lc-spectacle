package com.lc.spectacle.features.music_library.data.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class TracksRequestDto(
    @SerializedName("query") val query: String
)
