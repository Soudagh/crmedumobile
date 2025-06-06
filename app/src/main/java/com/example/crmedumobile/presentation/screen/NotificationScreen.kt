package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.NotificationItem
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    val viewModel: NotificationViewModel = hiltViewModel()
    val notifications = viewModel.notifications

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(stringResource(R.string.notifications), fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()

        LazyColumn(modifier = modifier.fillMaxSize().padding(top = 16.dp)   ) {
            itemsIndexed(notifications) {_, notification ->
                NotificationItem(notification = notification)
                Spacer(modifier = modifier.height(12.dp))
            }
        }
    }
}
