package com.lc.spectacle.features.music_library.data.repository

import com.lc.spectacle.features.music_library.data.remote.dto.TracksListDto
import com.lc.spectacle.features.music_library.data.remote.dto.requests.TracksRequestDto

interface ITracksRepository {
    suspend fun getTracks(query: TracksRequestDto): TracksListDto
    suspend fun getFavoriteTracks(): TracksListDto
    suspend fun addFavoriteTrack(): Boolean
    suspend fun removeFavoriteTrack(): Boolean
}