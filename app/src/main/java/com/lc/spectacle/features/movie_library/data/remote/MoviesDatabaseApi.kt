package com.lc.spectacle.features.movie_library.data.remote

import com.lc.spectacle.core.Constants
import com.lc.spectacle.core.commons.SingleQueryDto
import com.lc.spectacle.features.movie_library.data.remote.dto.GenresListDto
import com.lc.spectacle.features.movie_library.data.remote.dto.MoviesListDto
import retrofit2.Response
import retrofit2.http.*

interface MoviesDatabaseApi {
    companion object {
        const val BASE_URL = Constants.ApiBaseUrl
    }

    @GET("/get-movie-genres")
    @FormUrlEncoded
    fun genres(): Response<GenresListDto>

    @POST("/get-movies")
    @FormUrlEncoded
    fun searchMovie(
        @Body query: SingleQueryDto
    ): Response<MoviesListDto>
}