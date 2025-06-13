package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.RegularMontserrat14
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32

@Composable
fun StudentHomeworkScreenTutor(
//    homeworkItem: HomeworkItem,
    navController: NavHostController,
    onBack: () -> Unit
) {

    val homeworkItem = HomeworkItem(1, "Кирилл Рогачёв", "Подобные треугольники", "Непроверено")
    var score1 by remember { mutableStateOf(0) }
    var score2 by remember { mutableStateOf(0) }
    var score3 by remember { mutableStateOf(0) }


    val tasks = listOf(
        Triple("Задание: 1", score1, { newScore: Int -> score1 = newScore }),
        Triple("Задание: 2", score2, { newScore: Int -> score2 = newScore }),
        Triple("Задание: 3", score3, { newScore: Int -> score3 = newScore })
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Домашнее задание",
                style = SemiBoldMontserrat32,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalDimensions.current.verticalSmall)
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DarkPurple
        )


        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium)
        ) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = DarkPurple,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBack() }
                )


                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = homeworkItem.studentName,
                        style = RegularMontserrat16,
                        color = Color.Black
                    )
                    Text(
                        text = "Тема: ${homeworkItem.topic}",
                        style = RegularMontserrat14,
                        color = Color.Black
                    )
                    Text(
                        text = "Статус: ${homeworkItem.status}",
                        style = RegularMontserrat14,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(LocalDimensions.current.verticalMedium))


            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(tasks) { (task, score, updateScore) ->
                    var expanded by remember { mutableStateOf(false) }
                    val scoreOptions = listOf(1, 2, 3, 4, 5)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = task,
                            style = RegularMontserrat16,
                            color = DarkPurple
                        )
                        Box {
                            Text(
                                text = if (score == 0) "0/5" else score.toString(),
                                style = RegularMontserrat16,
                                color = Color.White,
                                modifier = Modifier
                                    .width(60.dp)
                                    .background(
                                        DarkPurple,
                                        RoundedCornerShape(LocalDimensions.current.defaultCornerRadius)
                                    )
                                    .padding(vertical = 8.dp)
                                    .clickable { expanded = true },
                                textAlign = TextAlign.Center
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                scoreOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = option.toString(),
                                                style = RegularMontserrat16
                                            )
                                        },
                                        onClick = {
                                            updateScore(option)
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Image(
                        painter = painterResource(id = R.drawable.dz),
                        contentDescription = "Заглушка задания",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall))
                }
            }
        }
    }
}

