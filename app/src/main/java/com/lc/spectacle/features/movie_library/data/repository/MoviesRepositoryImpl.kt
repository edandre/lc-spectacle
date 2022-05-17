package com.lc.spectacle.features.movie_library.data.repository

import com.lc.spectacle.core.commons.SingleQueryDto
import com.lc.spectacle.features.movie_library.data.remote.MoviesDatabaseApi
import com.lc.spectacle.features.movie_library.data.remote.dto.GenreDto
import com.lc.spectacle.features.movie_library.data.remote.dto.MoviesListDto
import com.lc.spectacle.features.movie_library.domain.repository.IMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDatabaseApi: MoviesDatabaseApi
) : IMoviesRepository {
    companion object {
        var genres: List<GenreDto>? = null
    }

    init {
        loadGenres()
    }

    private fun loadGenres() {
        runBlocking(Dispatchers.IO) {
            moviesDatabaseApi.genres().body()?.let {
                genres = it.genres
            }
        }
    }

    override fun searchMovie(query: String): MoviesListDto? {
        if (genres == null) {
            loadGenres()
        }

        val moviesResponse = moviesDatabaseApi.searchMovie(
            SingleQueryDto(
                query = query
            )
        )

        if (moviesResponse.isSuccessful) {
            return moviesResponse.body()
        }

        return null
    }

    override fun getFavoriteMovies(): MoviesListDto? {
        if (genres == null) {
            loadGenres()
        }

        return null
    }

    override fun addFavoriteMovie(id: Int): Boolean {
        if (genres == null) {
            loadGenres()
        }

        return true
    }

    override fun removeFavoriteMovie(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}