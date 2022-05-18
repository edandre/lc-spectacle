package com.lc.spectacle.core.ui.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login")
    object RegisterScreen: Screen("register")
    object HomeScreen: Screen("home")

    object MoviesScreen: Screen("movies")
    object AddMovieScreen: Screen("addMovie")

    object PlaylistsScreen: Screen("playlists")
    object AddTrackScreen: Screen("addTrack")
}
