package com.lc.spectacle.features.auth.data.repository

import com.google.firebase.auth.AuthResult
import com.lc.spectacle.core.commons.Resource
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import com.lc.spectacle.features.auth.data.remote.firebase.FirebaseAuthenticator
import com.lc.spectacle.features.auth.domain.repository.IAuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticator: FirebaseAuthenticator
) : IAuthenticationRepository {
    override fun authenticate(user: UserDto): AuthResult? {
        return authenticator.authenticate(user)
    }

    override fun register(user: UserDto): AuthResult? {
        return authenticator.register(user)
    }
}