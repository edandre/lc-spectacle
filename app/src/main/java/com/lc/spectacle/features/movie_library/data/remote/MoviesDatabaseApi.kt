package com.lc.spectacle.features.movie_library.data.remote

import com.lc.spectacle.features.movie_library.util.Constants
import com.lc.spectacle.features.movie_library.data.remote.dto.GenresListDto
import com.lc.spectacle.features.movie_library.data.remote.dto.MoviesListDto
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDatabaseApi {
    companion object {
        const val BASE_URL = Constants.MoviesApiBaseUrl
        const val API_VERSION = Constants.MoviesApiVersion
        const val API_KEY = Constants.MoviesApiKey
    }

    @GET("/$API_VERSION/genre/movie/list")
    @FormUrlEncoded
    fun genres(
        @Query("api_key") moviesApiKey: String = API_KEY,
        @Query("language") locale: String? = "pt-BR"
    ): Response<GenresListDto>

    @GET("/$API_VERSION/search/movie")
    @FormUrlEncoded
    fun movies(
        @Query("api_key") moviesApiKey: String = API_KEY,
        @Query("query") query: String,
        @Query("language") locale: String? = "pt-BR",
        @Query("page") page: Int? = 1
    ): Response<MoviesListDto>
}