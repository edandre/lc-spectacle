package com.lc.spectacle.features.auth.domain.repository

import com.google.firebase.auth.AuthResult
import com.lc.spectacle.features.auth.data.remote.dto.UserDto

interface IUserRepository {
    fun authenticate(user: UserDto, onSuccess: (input: AuthResult) -> Unit, onError: (errorMessage: String) -> Unit)
    fun register(user: UserDto, onSuccess: (input: AuthResult) -> Unit, onError: (errorMessage: String) -> Unit)
}