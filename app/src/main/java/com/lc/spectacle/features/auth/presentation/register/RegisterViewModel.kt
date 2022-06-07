package com.lc.spectacle.features.auth.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lc.spectacle.core.commons.Event
import com.lc.spectacle.core.commons.Extensions.isValidEmail
import com.lc.spectacle.core.commons.Resource
import com.lc.spectacle.features.auth.data.remote.dto.UserDto
import com.lc.spectacle.features.auth.domain.use_case.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
    //private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    /*private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage : LiveData<Event<String>>
        get() = _errorMessage*/

    private val _state = MutableLiveData<Event<RegisterState>>()
    val state: LiveData<Event<RegisterState>> get() = _state

    private val baseCoroutineScopeMain = CoroutineScope(Dispatchers.Main.immediate)
    var email: String = ""
    var password: String = ""
    var passwordMatch: String = ""

//    init {
//        savedStateHandle.get<String>(Constants.REGISTER_EMAIL_ID)?.let { email ->
//            savedStateHandle.get<String>(Constants.REGISTER_PASSWORD_ID)?.let { password ->
//                register(email, password)
//            }
//        }
//    }

    private fun passwordsMatch(): Boolean {
        return password == passwordMatch
    }

    private fun userIsValid(): Boolean {
        return email.isValidEmail() && password.isNotEmpty() && password.isNotBlank()
    }

    fun register() {
        baseCoroutineScopeMain.launch {
            if (!passwordsMatch()) {
                _state.value = Event(RegisterState(
                    isLoading = false,
                    successfullyRegistered = false,
                    error = "Senhas não conferem"
                ))

                return@launch
            }

            if (!userIsValid()) {
                _state.value = Event(RegisterState(
                    isLoading = false,
                    successfullyRegistered = false,
                    error = "Dados inválidos"
                ))

                return@launch
            }

            registerUseCase(UserDto(
                userId = "",
                email = email,
                password = password
            )).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = Event(RegisterState(
                            isLoading = false,
                            successfullyRegistered = true,
                            error = ""
                        ))
                    }
                    is Resource.Error -> {
                        _state.value = Event(RegisterState(
                            isLoading = false,
                            successfullyRegistered = false,
                            error = result.message ?: "Opa, algo saiu errado :/"
                        ))
                    }
                    is Resource.Loading -> {
                        _state.value = Event(RegisterState(
                            isLoading = true,
                            successfullyRegistered = false,
                            error = ""
                        ))
                    }
                }
            }.launchIn(this)
        }
    }
}