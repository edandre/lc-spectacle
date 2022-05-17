package com.lc.spectacle.features.movie_library.domain.repository

import com.lc.spectacle.features.movie_library.data.remote.dto.MoviesListDto

interface IMoviesRepository {
    fun searchMovie(query: String): MoviesListDto?
    fun getFavoriteMovies(): MoviesListDto?
    fun addFavoriteMovie(id: Int): Boolean
    fun removeFavoriteMovie(id: Int): Boolean
}