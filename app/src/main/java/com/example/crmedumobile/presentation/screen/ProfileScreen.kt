package com.example.crmedumobile.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.presentation.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    viewModel: UserViewModel = hiltViewModel(),
) {
    val userState by viewModel.userState.collectAsState()

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}