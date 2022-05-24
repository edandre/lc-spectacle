package com.lc.spectacle.features.movie_library.domain.model

data class MovieTileModel(
    val movie: Movie,
    var isFavorite: Boolean = false
)
