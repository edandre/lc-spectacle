package com.lc.spectacle.features.auth.presentation

sealed class Screen(val route: String) {
    object LoginScreen: Screen ("login")
    object RegisterScreen: Screen ("register")
    object HomeScreen: Screen ("home")

    object MoviesScreen: Screen("movies")
    object AddMovieScreen: Screen("movies")

    object PlaylistsScreen: Screen("playlists")
    object AddTrackScreen: Screen("playlists")
}
