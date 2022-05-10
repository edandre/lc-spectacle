package com.lc.spectacle.features.movie_library.domain.repository

import com.lc.spectacle.features.movie_library.data.remote.dto.GenresListDto
import com.lc.spectacle.features.movie_library.data.remote.dto.MoviesListDto

interface IMoviesRepository {
    suspend fun getMovies(): MoviesListDto
    suspend fun getGenres(): GenresListDto
    suspend fun addFavoriteMovie()
    suspend fun removeFavoriteMovie()
}