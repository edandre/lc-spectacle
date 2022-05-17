package com.lc.spectacle.features.auth.presentation.register

data class RegisterState (
    var isLoading: Boolean = false,
    var successfullyRegistered: Boolean = false,
    var error: String = ""
)