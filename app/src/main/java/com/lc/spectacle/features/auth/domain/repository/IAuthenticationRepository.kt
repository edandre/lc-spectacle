package com.lc.spectacle.features.auth.domain.repository

import com.google.firebase.auth.AuthResult
import com.lc.spectacle.core.commons.Resource
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface IAuthenticationRepository {
    fun authenticate(user: UserDto): AuthResult?
    fun register(user: UserDto): AuthResult?
}