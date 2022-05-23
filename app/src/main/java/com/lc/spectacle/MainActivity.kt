package com.lc.spectacle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lc.spectacle.core.ui.components.HomeScreen
import com.lc.spectacle.core.ui.navigation.Screen
import com.lc.spectacle.features.auth.presentation.login.components.LoginScreen
import com.lc.spectacle.features.auth.presentation.register.components.RegisterScreen
import com.lc.spectacle.features.movie_library.presentation.movies.components.MoviesScreen
import com.lc.spectacle.ui.theme.SpectacleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lifecycleOwner: LifecycleOwner = this

        setContent {
            SpectacleTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = MaterialTheme.colors.primary,
                    darkIcons = true
                )

                systemUiController.setNavigationBarColor(
                    color = MaterialTheme.colors.secondaryVariant
                )

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.LoginScreen.route
                ) {
                    composable(
                        route = Screen.LoginScreen.route,
                    ) {
                        LoginScreen(lifecycleOwner, navController)
                    }

                    composable(
                        route = Screen.RegisterScreen.route,
                    ) {
                        RegisterScreen(lifecycleOwner, navController)
                    }

                    composable(
                        route = Screen.HomeScreen.route,
                    ) {
                        HomeScreen(navController)
                    }

                    composable(
                        route = Screen.MoviesScreen.route,
                    ) {
                        MoviesScreen()
                    }
                }
            }
        }
    }
}