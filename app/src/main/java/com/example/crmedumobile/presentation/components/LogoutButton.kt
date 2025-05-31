package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.theme.Black
import com.example.crmedumobile.presentation.theme.BoldMontserrat24
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Purple
import com.example.crmedumobile.presentation.theme.paddingButton

@Composable
fun LogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .width(250.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple,
            contentColor = Black,
        ),
        border = BorderStroke(1.dp, DarkPurple)
    ) {
        Text(
            text = "Выйти",
            style = BoldMontserrat24
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutButtonPreview() {
    LogoutButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingButton)
    )
}