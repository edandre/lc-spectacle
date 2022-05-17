package com.lc.spectacle.features.music_library.domain.model

data class Album(
    val id: String,
    val albumType: String,
    val imagesUrls: List<String>,
    val name: String,
    val releaseDate: String
)