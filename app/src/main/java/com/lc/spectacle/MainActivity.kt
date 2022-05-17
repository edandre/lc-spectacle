package com.lc.spectacle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lc.spectacle.features.auth.presentation.Screen
import com.lc.spectacle.features.auth.presentation.login.components.LoginScreen
import com.lc.spectacle.features.auth.presentation.register.components.RegisterScreen
import com.lc.spectacle.ui.theme.SpectacleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpectacleTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.LoginScreen.route
                ) {
                    composable(
                        route = Screen.LoginScreen.route,
                    ) {
                        LoginScreen(navController)
                    }

                    composable(
                        route = Screen.RegisterScreen.route,
                    ) {
                        RegisterScreen(navController)
                    }
                }
            }
        }
    }
}