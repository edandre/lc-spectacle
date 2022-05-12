package com.lc.spectacle.features.auth.data.repository

import com.google.firebase.auth.AuthResult
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import com.lc.spectacle.features.auth.data.remote.firebase.FirebaseAuthenticator
import com.lc.spectacle.features.auth.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authenticator: FirebaseAuthenticator
) : IUserRepository {
    private var loggedUser: UserDto? = null

    override fun authenticate(user: UserDto, onSuccess: (input: AuthResult) -> Unit, onError: (errorMessage: String) -> Unit) {
        authenticator.authenticate(user, onSuccess, onError)
    }

    override fun register(user: UserDto, onSuccess: (input: AuthResult) -> Unit, onError: (errorMessage: String) -> Unit) {
        authenticator.register(user, onSuccess, onError)
    }

    override fun setLoggedUser(user: UserDto) {
        loggedUser = user
    }

    override fun getLoggedUser(): UserDto? {
        return loggedUser
    }
}