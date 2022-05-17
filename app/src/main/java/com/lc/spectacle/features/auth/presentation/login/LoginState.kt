package com.lc.spectacle.features.auth.presentation.login

data class LoginState (
    var isLoading: Boolean = false,
    var successfullyLoggedIn: Boolean = false,
    var error: String = ""
)