package com.lc.spectacle.features.auth.presentation.login.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.lc.spectacle.R
import com.lc.spectacle.core.ui.navigation.Screen
import com.lc.spectacle.features.auth.presentation.login.LoginViewModel
import java.util.Locale

@Preview
@Composable
fun LoginScreen(
    lifecycleOwner: LifecycleOwner,
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var loading = false

    loginViewModel.state.observe(lifecycleOwner, Observer {
        it.getContentIfNotHandled()?.let { state ->
            if (state.error.isNotEmpty()) {
                Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()

                return@Observer
            }

            loading = state.isLoading

            if (state.successfullyLoggedIn) {
                navController.navigate(Screen.HomeScreen.route)
            }
        }
    })

    val pageScrollState = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
                title = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.app_name).uppercase(Locale.ROOT),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .verticalScroll(pageScrollState)
                        .padding(horizontal = 16.dp)
                        .padding(top = 164.dp)
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    Alignment.CenterHorizontally,
                ) {
                    Title()
                    Email(setEmail = {
                        loginViewModel.email = it
                    })
                    Password(setPassword = {
                        loginViewModel.password = it
                    })
                    Register(navController)
                    if (loading) {
                        LoadingSignInButton()
                    } else {
                        SignIn(onClick = {
                            loginViewModel.login()
                        })
                    }
                }
            }
        }
    )
}

@Composable fun Title() {
    Text (
        text = stringResource(id = R.string.welcome_to_app),
        color = Color.White
    )
}

@Composable fun Email(setEmail: (updatedEmail: String) -> Unit) {
    val focusManager = LocalFocusManager.current

    val emailState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.65f),
        value = emailState.value,
        onValueChange = {
            emailState.value = it
            setEmail(it.text)
        },
        label = { Text( text = stringResource(R.string.email_hint)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White,
            focusedLabelColor = Color.White,
            cursorColor = MaterialTheme.colors.secondaryVariant
        ),
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
    )
}

@Composable fun Password(setPassword: (updatedPassword: String) -> Unit) {
    val focusManager = LocalFocusManager.current
    val pwdState = remember { mutableStateOf(TextFieldValue()) }
    val showPassword = remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.65f),
        value = pwdState.value,
        maxLines = 1,
        onValueChange = {
            pwdState.value = it
            setPassword(it.text)
        },
        label = { Text( text = stringResource(R.string.password_hint)) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White,
            focusedLabelColor = Color.White,
            cursorColor = MaterialTheme.colors.secondaryVariant
        ),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (showPassword.value) { VisualTransformation.None } else {PasswordVisualTransformation()},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(true)
            }
        ),
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = {showPassword.value = false}) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        ""
                    )
                }
            } else {
                IconButton(onClick = {showPassword.value = true}) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        ""
                    )
                }
            }
        }
    )
}

@Composable fun Register(navController: NavController) {
    ClickableText(
        modifier = Modifier
            .padding(vertical= 4.dp),
        text = AnnotatedString(stringResource(id = R.string.register_text)),
        style = TextStyle(
            color = Color.White
        ),
        onClick = {
            navController.navigate(Screen.RegisterScreen.route)
        },
    )
}

@Composable fun SignIn(onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .alpha(0.75f)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = Color.White
        )
    ) {
        Text (
            text = stringResource(id = R.string.sign_in_button_text),
            style = TextStyle(
                Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
        )
    }
}

@Composable fun LoadingSignInButton() {
    Button (
        onClick = { },
        modifier = Modifier
            .height(48.dp)
            .alpha(0.75f)
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = Color.White
        )
    ) {
        CircularProgressIndicator(
            color = Color.White
        )
    }
}