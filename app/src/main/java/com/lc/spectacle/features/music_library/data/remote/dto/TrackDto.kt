package com.lc.spectacle.features.music_library.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.lc.spectacle.features.music_library.domain.model.Track

data class TrackDto(
    @SerializedName("album")
    val albumDto: AlbumDto,
    @SerializedName("artists")
    val artists: List<ArtistDto>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("disc_number")
    val discNumber: Int,
    @SerializedName("duration_ms")
    val durationMs: Int,
    @SerializedName("explicit")
    val explicit: Boolean,
    @SerializedName("external_ids")
    val externalIds: ExternalIdsDto,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrlsDto,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: Any,
    @SerializedName("track_number")
    val trackNumber: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
)

fun TrackDto.toTrack(): Track {
    return Track(
        id = id,
        name = name,
        album = albumDto.toAlbum(),
        artists = artists.map { it.toArtist() },
        explicit = explicit,
        spotifyUrl = externalUrls.spotify
    )
}