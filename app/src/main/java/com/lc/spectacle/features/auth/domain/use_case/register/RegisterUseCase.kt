package com.lc.spectacle.features.auth.domain.use_case.register

import android.util.Log
import com.lc.spectacle.core.commons.Resource
import com.lc.spectacle.core.identity.Session
import com.lc.spectacle.core.identity.model.User
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import com.lc.spectacle.features.auth.domain.repository.IAuthenticationRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.coroutineContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RegisterUseCase @Inject constructor(
    private val repository: IAuthenticationRepository
) {
    operator fun invoke(userDto: UserDto): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            repository.register(userDto)?.let { registerResult ->
                registerResult.user?.let { user ->
                    Session.currentUser = User(
                        userId = user.uid,
                        username = user.displayName ?: "No display name"
                    )

                    emit(Resource.Success(true))

                    return@flow
                }
            }

            // no error, but not authenticated
            emit(Resource.Error("Dados inválidos", false))
        } catch (e: Exception){
            Log.e("LoginError", "${e.message}\n${e.stackTraceToString()}\n")
            emit(Resource.Error("Opa, algo deu errado :/", false))
        }
    }
}