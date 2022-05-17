package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.lc.spectacle.features.music_library.domain.model.Album

data class AlbumDto(
    @SerializedName("album_type")
    val albumType: String,
    @SerializedName("artists")
    val artists: List<ArtistDto>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsDto,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageDto>,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

fun AlbumDto.toAlbum() : Album {
    return Album(
        id = id,
        albumType = albumType,
        imagesUrls = images.map { it.url },
        name = name,
        releaseDate = releaseDate
    )
}