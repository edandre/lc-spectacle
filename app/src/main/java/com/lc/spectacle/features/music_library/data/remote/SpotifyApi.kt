package com.lc.spectacle.features.music_library.data.remote

import com.lc.spectacle.core.Constants
import com.lc.spectacle.features.music_library.data.remote.dto.TracksListDto
import com.lc.spectacle.features.music_library.data.remote.dto.requests.FavoriteTrackRequestDto
import com.lc.spectacle.features.music_library.data.remote.dto.requests.TracksRequestDto
import retrofit2.Response
import retrofit2.http.*

interface SpotifyApi {
    companion object {
        const val BASE_URL = Constants.ApiBaseUrl
    }

    @POST("/get-tracks")
    fun tracks(
        @Body tracks: TracksRequestDto
    ): Response<TracksListDto>

    @POST("/add-favorite-track")
    fun addFavoriteTrack(
        @Body track: FavoriteTrackRequestDto
    ): Response<String>

    @POST("/remove-favorite-track")
    fun removeFavoriteTrack(
        @Body track: FavoriteTrackRequestDto
    ): Response<String>

    @POST("/get-favorite-tracks")
    fun favoriteTracks(
        @Query("uId") userId: String
    ): Response<TracksListDto>
}