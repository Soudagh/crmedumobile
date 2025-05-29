package com.example.crmedumobile.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.components.ScheduleItem
import com.example.crmedumobile.presentation.state.ScheduleState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.theme.Purple40
import com.example.crmedumobile.presentation.viewmodel.ScheduleViewModel

@Composable
fun ScheduleScreen(
    controller: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val viewModel = hiltViewModel<ScheduleViewModel>()
    var list by remember { mutableStateOf(listOf<ScheduleModel>()) }
    var today by remember { mutableStateOf<Pair<String,String>>(Pair("","")) }
    LaunchedEffect(viewModel.state) {
        viewModel.state.collect {
            when(it){
                is ScheduleState.Error -> {
                    println("Error: ${it.message}")
                }
                is ScheduleState.Success -> {
                    list = it.schedule
                    today = it.today
                }
                else -> {}
            }
        }
    }
    ScheduleScreenContent(list = list, today = today)
    BackHandler {
        controller.popBackStack()
    }
}

@Composable
fun ScheduleScreenContent(
    modifier: Modifier = Modifier,
    list: List<ScheduleModel>,
    today: Pair<String, String>
) {
    Column(modifier = modifier
        .fillMaxSize()) {
        Text(stringResource(R.string.schedule), fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {

            }, modifier = modifier.weight(1f)) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft, null, tint = Black)
            }

            Column(modifier = modifier
                .weight(11f)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(today.first, color = Purple40)
                Text(today.second, color = Purple40)
            }

            IconButton(onClick = {

            }, modifier = modifier.weight(1f)) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, null, tint = Black)
            }
        }
        HorizontalDivider()
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(list) { _, item ->
                ScheduleItem(item = item)
            }

        }
    }
}
@Preview(showBackground = false)
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen()
}