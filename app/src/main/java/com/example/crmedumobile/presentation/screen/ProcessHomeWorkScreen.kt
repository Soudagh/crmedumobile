package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScienceModel
import com.example.crmedumobile.domain.model.TheoryModel
import com.example.crmedumobile.domain.model.enums.StatusScience
import com.example.crmedumobile.presentation.components.TheoryItem
import com.example.crmedumobile.presentation.state.TheoryUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.TheoryViewModel

@Composable
fun TheoryScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController,
    backStackEntry: NavBackStackEntry
) {
    val viewModel = hiltViewModel<TheoryViewModel>()
    val id = backStackEntry.arguments?.getInt("id") ?: 0
    viewModel.getTheory(id)
    var item by remember {
        mutableStateOf<ScienceModel>(ScienceModel("", "", StatusScience.NotSubmitted))
    }
    var list by remember {
        mutableStateOf<List<TheoryModel>>(emptyList())
    }
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stringResource(R.string.homeWork),
            fontSize = 28.sp,
            color = Black,
            style = BoldMontserrat36,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        Row(modifier.fillMaxWidth()) {
            IconButton(onClick = {
                controller.popBackStack()
            }, modifier = modifier
                .weight(2f)
                .align(Alignment.CenterVertically)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Black)
            }
            Text(
                item.theme,
                fontSize = 19.sp,
                color = Black,
                style = BoldMontserrat36,
                modifier = modifier
                    .weight(10f)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
        HorizontalDivider()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(list.size) {
                TheoryItem(theoryModel = list[it])
            }
        }
        Button(
            onClick = {
                controller.navigate("finishTask")
            },
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFD2F4D1)),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(stringResource(R.string.submit), fontSize = 16.sp, color = Black)
        }

    }
    LaunchedEffect(viewModel.uiState) {
        viewModel.uiState.collect {
            when (it) {
                is TheoryUiState.Error -> print("Error: ${it.message}")
                is TheoryUiState.Success -> {
                    item = it.scienceModel
                    list = it.data
                }

                else -> {}
            }
        }
    }
}