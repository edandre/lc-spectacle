package com.lc.spectacle.features.movie_library.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.lc.spectacle.core.Constants
import com.lc.spectacle.features.movie_library.data.repository.MoviesRepositoryImpl
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
        genres = genreIds.map { genreId -> MoviesRepositoryImpl.genres?.find { it.id == genreId }?.name ?: "" },
        overview = overview,
        posterUrl = "${Constants.MoviesApiImagesRepositoryBaseUrl}$posterPath",
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}