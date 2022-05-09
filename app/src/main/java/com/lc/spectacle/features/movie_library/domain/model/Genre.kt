package com.lc.spectacle.features.movie_library.domain.model

import androidx.room.Entity

@Entity
data class Genre (
    val genreId: Int,
    val genreName: String
)