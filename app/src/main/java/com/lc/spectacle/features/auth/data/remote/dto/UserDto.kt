package com.lc.spectacle.features.auth.data.remote.dto

import com.lc.spectacle.core.identity.model.User

data class UserDto (
    val userId: String,
    val email: String,
    val password: String
)

fun UserDto.toUser(): User {
    return User(
        userId = userId,
        username = email,
    )
}