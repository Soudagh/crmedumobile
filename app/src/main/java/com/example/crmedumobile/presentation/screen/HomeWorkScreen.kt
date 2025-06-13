package com.example.crmedumobile.presentation.screen

import PrimaryButton
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.BoldMontserrat36

@Composable
fun HomeWorkScreen(modifier: Modifier = Modifier, controller: NavHostController) {
    Column(modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.homeWork),
            fontSize = 28.sp,
            color = Black,
            style = BoldMontserrat36,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        PrimaryButton(stringResource(R.string.hw), onButtonClick = {
            controller.navigate("elements")
        }, modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(80.dp))
        PrimaryButton(stringResource(R.string.controls), onButtonClick = {

        }, modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(80.dp))
        PrimaryButton(stringResource(R.string.theory), onButtonClick = {
        }, modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(80.dp))
        PrimaryButton(stringResource(R.string.readyVariants), onButtonClick = {

        }, modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(80.dp))
    }
    BackHandler {
        controller.popBackStack()
    }
}