package com.lc.spectacle.features.auth.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lc.spectacle.features.auth.presentation.register.RegisterViewModel

@Composable
fun RegisterCard(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val state = registerViewModel.state.value
}