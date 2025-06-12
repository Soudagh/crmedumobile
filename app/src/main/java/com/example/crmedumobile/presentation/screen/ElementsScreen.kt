package com.example.crmedumobile.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.state.ElementsUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.theme.CrmEduMontserrat
import com.example.crmedumobile.presentation.viewmodel.ElementsViewModel

@Composable
fun ElementsScreen(modifier: Modifier = Modifier, controller: NavHostController) {
    val viewModel = hiltViewModel<ElementsViewModel>()
    var list by remember {
        mutableStateOf(listOf<String>())
    }
    Column(modifier.fillMaxSize()) {
        Text(stringResource(R.string.homeWork), fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Text(stringResource(R.string.elements), fontSize = 19.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Center)
        HorizontalDivider()
        LazyColumn {
            itemsIndexed(list) { index,item->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(vertical = 1.dp, horizontal = 5.dp).clickable{
                    controller.navigate("science/$index")
                }) {
                    Text(item, fontFamily = CrmEduMontserrat, fontSize = 24.sp, color = Black, modifier = modifier.weight(11f).fillMaxWidth())
                    Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, null, tint = Black, modifier = modifier.weight(1f))
                }
            }
        }
    }
    LaunchedEffect(viewModel.uiState) {
        viewModel.uiState.collect {
            when (it) {
                is ElementsUiState.Error -> {
                    println("Error: ${it.message}")
                }

                is ElementsUiState.Success -> {
                    list = it.elements
                }

                else -> {}
            }
        }
    }
    BackHandler {
        controller.popBackStack()
    }
}