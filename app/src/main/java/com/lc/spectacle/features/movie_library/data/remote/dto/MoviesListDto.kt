package com.lc.spectacle.features.movie_library.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)