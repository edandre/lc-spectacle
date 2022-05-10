package com.lc.spectacle.features.movie_library.data.remote.dto

import com.lc.spectacle.features.movie_library.domain.model.Genre

data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.toGenre(): Genre {
    return Genre(
        genreId = id,
        genreName = name
    )
}