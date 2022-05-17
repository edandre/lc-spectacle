package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.lc.spectacle.features.music_library.domain.model.Artist

data class ArtistDto(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsDto,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

fun ArtistDto.toArtist() : Artist {
    return Artist(
        id = id,
        name = name
    )
}