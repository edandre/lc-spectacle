package com.lc.spectacle.features.movie_library.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.lc.spectacle.features.movie_library.util.Constants
import com.lc.spectacle.features.movie_library.domain.model.Genre
import com.lc.spectacle.features.movie_library.domain.model.Movie

data class MovieDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun MovieDto.toMovie() : Movie {
    return Movie(
        movieId = id,
        genre = Genre(genreId = 0, genreName = ""), //TODO: USING DI, TURN GENRES ACCESSIBLE ON ITS REPOSITORY AND THEN FILTER BY THE FIRST GENRE ID
        overview = overview,
        posterUrl = "${Constants.MoviesApiImagesRepositoryBaseUrl}$posterPath",
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}