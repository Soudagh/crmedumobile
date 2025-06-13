package com.example.crmedumobile.presentation.screen

import LocalDimensions
import PrimaryButton
import PrimaryTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.state.LoginUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginUiState.Success -> {
                viewModel.getRole { role ->
                    onLoginSuccess(role)
                }
                println("Успешный вход: ${(loginState as LoginUiState.Success).jwt}")
            }

            is LoginUiState.Error -> {
                val message = (loginState as LoginUiState.Error).message
                scope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            }

            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        LoginScreenContent(
            modifier = Modifier.padding(padding),
            loginState = loginState,
            onLogin = { email, password -> viewModel.login(email, password) }
        )
    }
}


@Composable
fun LoginScreenContent(
    loginState: LoginUiState,
    onLogin: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var isLoginError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    val dimensions = LocalDimensions.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensions.horizontalMedium),
        verticalArrangement = Arrangement.spacedBy(
            dimensions.verticalSmall,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            style = BoldMontserrat36,
            text = stringResource(R.string.enter)
        )

        PrimaryTextField(
            title = stringResource(R.string.log_in),
            onTextChange = {
                login = it
                isLoginError = false
            },
            value = login,
            isError = isLoginError,
            errorText = if (isLoginError) stringResource(R.string.login_field_blank_error) else null
        )

        PrimaryTextField(
            title = stringResource(R.string.password),
            onTextChange = {
                password = it
                isPasswordError = false
            },
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            trailingIcon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            onTrailingIconClicked = { passwordVisible = !passwordVisible },
            isError = isPasswordError,
            errorText = if (isPasswordError) stringResource(R.string.password_field_blank_error) else null
        )

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.defaultPadding),
            text = stringResource(R.string.login),
            onButtonClick = {
                var hasError = false

                if (login.isBlank()) {
                    isLoginError = true
                    hasError = true
                }

                if (password.isBlank()) {
                    isPasswordError = true
                    hasError = true
                }

                if (!hasError) {
                    onLogin(login, password)
                }
            }
        )

        if (loginState is LoginUiState.Loading) {
            CircularProgressIndicator()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        loginState = LoginUiState.Idle,
        onLogin = { _, _ -> }
    )
}