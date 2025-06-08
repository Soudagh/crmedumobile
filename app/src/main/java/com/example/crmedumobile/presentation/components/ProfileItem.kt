package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.crmedumobile.presentation.theme.RegularMontserrat24

@Composable
fun ProfileItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "$label: ",
            style = RegularMontserrat24,
            color = Color.Black
        )
        Text(
            text = value,
            style = RegularMontserrat24,
            color = Color.Black
        )
    }
}