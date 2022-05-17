package com.lc.spectacle.features.music_library.domain.model

data class Track(
    val id: String,
    val album: Album,
    val artists: List<Artist>,
    val explicit: Boolean,
    val spotifyUrl: String,
    val name: String
)
