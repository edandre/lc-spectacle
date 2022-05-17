package com.lc.spectacle.features.music_library.data.remote.dto.requests

import com.google.gson.annotations.SerializedName
import com.lc.spectacle.features.music_library.domain.model.Track

data class FavoriteTrackRequestDto(
    @SerializedName("userId") val userId: String,
    @SerializedName("trackId") val trackId: String,
    @SerializedName("trackName") val trackName: String,
    @SerializedName("spotifyLink") val spotifyLink: String
)

fun FavoriteTrackRequestDto.fromTrack(userId: String, track: Track): FavoriteTrackRequestDto {
    return FavoriteTrackRequestDto(
        userId = userId,
        trackId = track.id,
        trackName = track.name,
        spotifyLink = track.spotifyUrl
    )
}
