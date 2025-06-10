package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.BottomBarItem

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, controller: NavHostController) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val list = listOf(
        BottomBarItem(
            stringResource(R.string.homeWork2),
            R.drawable.ic_homework,
            "homeWork"
        ),
        BottomBarItem(
            stringResource(R.string.notifications),
            R.drawable.ic_notification,
            "notifications"
        ),
        BottomBarItem(
            stringResource(R.string.schedule),
            R.drawable.ic_schedule,
            "schedule"
        ),
        BottomBarItem(
            stringResource(R.string.profile),
            R.drawable.ic_profile,
            "profile"
        )
    )
    NavigationBar(
        modifier = modifier,
    ) {
        list.forEachIndexed {index,item->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    controller.navigate(item.route)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painterResource(item.icon), null, tint = Black)
                        if(selectedItemIndex == index){
                            Text(item.title, fontSize = 10.sp)
                        }
                    }
                }
            )
        }
    }
}