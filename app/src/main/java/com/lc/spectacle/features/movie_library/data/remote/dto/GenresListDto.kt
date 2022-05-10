package com.lc.spectacle.features.movie_library.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GenresListDto(
    val genres: List<GenreDto>
)