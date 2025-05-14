package com.example.crmedumobile.presentation.screen

import LocalDimensions
import PrimaryButton
import PrimaryTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.states.LoginUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginUiState.Success -> {
                onLoginSuccess()
                println("Успешный вход: ${(loginState as LoginUiState.Success).jwt}")
            }

            is LoginUiState.Error -> {
                println("Ошибка: ${(loginState as LoginUiState.Error).message}")
                // TODO: показать Snackbar или диалог
            }

            else -> {}
        }
    }

    LoginScreenContent(
        loginState = loginState,
        onLogin = { email, password -> viewModel.login(email, password) },
    )
}

@Composable
fun LoginScreenContent(
    loginState: LoginUiState,
    onLogin: (String, String) -> Unit,
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val dimensions = LocalDimensions.current


    Column(
        modifier = Modifier
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
            onTextChange = { login = it },
            value = login
        )
        PrimaryTextField(
            title = stringResource(R.string.password),
            onTextChange = { password = it },
            value = password
        )
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.defaultPadding),
            text = stringResource(R.string.login),
            onButtonClick = {
                onLogin(login, password)
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