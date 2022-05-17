package com.lc.spectacle.features.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lc.spectacle.core.commons.Extensions.isValidEmail
import com.lc.spectacle.core.commons.Resource
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import com.lc.spectacle.features.auth.domain.use_case.login.LoginUseCase
import com.lc.spectacle.features.auth.presentation.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val baseCoroutineScopeMain = CoroutineScope(Dispatchers.Main.immediate)
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    var email: String = ""
    var password: String = ""

    private fun userIsValid(): Boolean {
        return email.isValidEmail() && password.isNotEmpty() && password.isNotBlank()
    }

    fun login() {
        baseCoroutineScopeMain.launch {
            if (!userIsValid()) {
                _state.value = LoginState(
                    isLoading = false,
                    successfullyLoggedIn = false,
                    error = "Dados inválidos"
                )
                return@launch
            }

            loginUseCase(
                UserDto(
                    "",
                    email,
                    password
                )
            ).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = LoginState(
                            isLoading = false,
                            successfullyLoggedIn = true,
                            error = ""
                        )
                    }
                    is Resource.Error -> {
                        _state.value = LoginState(
                            isLoading = false,
                            successfullyLoggedIn = false,
                            error = result.message ?: "Opa, algo saiu errado :/"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = LoginState(
                            isLoading = true,
                            successfullyLoggedIn = false,
                            error = ""
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}