package com.example.crmedumobile.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScienceModel
import com.example.crmedumobile.presentation.state.ScienceState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.ScienceViewModel

@Composable
fun ScienceScreen(modifier: Modifier = Modifier, controller: NavHostController, backStackEntry: NavBackStackEntry) {
    val id = backStackEntry.arguments?.getInt("id", 0) ?: 0
    val viewModel = hiltViewModel<ScienceViewModel>()
    viewModel.findElement(id)
    var science by remember { mutableStateOf("") }
    var list by remember { mutableStateOf(listOf<ScienceModel>()) }
    Column(modifier.fillMaxSize()) {
        Text(stringResource(R.string.homeWork), fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Text(science, fontSize = 19.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center)
        HorizontalDivider()
        LazyColumn {
            itemsIndexed(list)  {index, item ->
                Card(modifier = modifier.fillMaxWidth().padding(10.dp, vertical = 5.dp), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(
                    colorResource(R.color.schedaleBackgroundColor)
                )) {
                    Row(modifier.fillMaxWidth()) {
                        Column(modifier.fillMaxWidth().weight(10f)) {
                            Text(item.theme, fontSize = 16.sp, color = Black, style = BoldMontserrat36, modifier = modifier.padding(10.dp))
                            Text(String.format(stringResource(R.string.validate), item.validate), fontSize = 16.sp, color = Black, style = BoldMontserrat36, modifier = modifier.padding(10.dp))
                            Row(modifier = modifier.padding(10.dp)) {
                                Text(stringResource(R.string.status), fontSize = 16.sp, color = Black, style = BoldMontserrat36)
                                Text(if (item.status.ordinal == 0) stringResource(R.string.passed) else stringResource(R.string.failed), fontSize = 16.sp, color = if (item.status.ordinal == 0) colorResource(R.color.green) else colorResource(R.color.red), style = BoldMontserrat36)
                            }
                        }
                        IconButton(onClick = {

                        }, modifier = modifier.weight(2f).align(Alignment.CenterVertically)) {
                            Icon(painter = painterResource(R.drawable.ic_edit), null, tint = Black)
                        }
                    }
                }
            }
        }
    }
    LaunchedEffect(viewModel.uiState) {
        viewModel.uiState.collect {
            when (it) {
                is ScienceState.Success -> {
                    science = it.science
                    list = it.data
                }
                is ScienceState.Error->{
                    print("Error: ${it.message}")
                }
                else -> {}
            }
        }
    }
    BackHandler {
        controller.popBackStack()
    }
}