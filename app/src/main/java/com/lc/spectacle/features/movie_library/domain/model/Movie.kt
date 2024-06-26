package com.lc.spectacle.features.movie_library.domain.model

data class Movie (
    val movieId: Int,
    val title: String,
    val posterUrl: String,
    val genres: List<String>,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String
)