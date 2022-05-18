package com.lc.spectacle.features.auth.presentation.login

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lc.spectacle.core.commons.Event
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
    private val _state = MutableLiveData<Event<LoginState>>()
    val state : LiveData<Event<LoginState>>
        get() = _state

    private val baseCoroutineScopeMain = CoroutineScope(Dispatchers.Main.immediate)
    var email: String = ""
    var password: String = ""


    private fun userIsValid(): Boolean {
        return email.isValidEmail() && password.isNotEmpty() && password.isNotBlank()
    }

    fun login() {
        baseCoroutineScopeMain.launch {
            if (!userIsValid()) {
                _state.value = Event(LoginState(
                    isLoading = false,
                    successfullyLoggedIn = false,
                    error = "Dados inválidos"
                ))

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
                        _state.value = Event(LoginState(
                            isLoading = false,
                            successfullyLoggedIn = true,
                            error = ""
                        ))
                    }
                    is Resource.Error -> {
                        _state.value = Event(LoginState(
                            isLoading = false,
                            successfullyLoggedIn = false,
                            error = result.message ?: "Opa, algo saiu errado :/"
                        ))
                    }
                    is Resource.Loading -> {
                        _state.value = Event(LoginState(
                            isLoading = true,
                            successfullyLoggedIn = false,
                            error = ""
                        ))
                    }
                }
            }.launchIn(this)
        }
    }
}