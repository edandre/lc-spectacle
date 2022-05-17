package com.lc.spectacle.features.movie_library.data.remote.dto

data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.toString(): String {
    return name
}