package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.components.HomeworkItemCard
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.theme.*
import kotlinx.serialization.Serializable

@Serializable
data class HomeworkItem(
    val studentName: String,
    val topic: String,
    val status: String
)

@Composable
fun CheckHomeworkScreen(
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit,
    onHomeworkSelected: (HomeworkItem) -> Unit
) {
    val homeworkItems = listOf(
        HomeworkItem("Кирилл Рогачёв", "Подобные треугольники", "Непроверено"),
        HomeworkItem("Анна Смирнова", "Квадратные уравнения", "Проверено"),
        HomeworkItem("Иван Петров", "Векторы", "Непроверено")
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
                text = "Проверить ДЗ",
                style = SemiBoldMontserrat32,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalDimensions.current.verticalSmall)
            )
        }
        Divider(
            color = DarkPurple,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium)
        ) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = DarkPurple,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onBack() }
                )

                TextField(
                    value = "",
                    onValueChange = { /* TODO: Логика поиска */ },
                    placeholder = {
                        Text(
                            text = "Укажите ФИО ученика",
                            style = RegularMontserrat16,
                            color = Gray
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = DarkPurple
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                        .border(
                            width = 1.dp,
                            color = DarkPurple,
                            shape = RoundedCornerShape(LocalDimensions.current.defaultCornerRadius)
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = RegularMontserrat16
                )
            }
            Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall))
        }


        Divider(
            color = DarkPurple,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall))


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(homeworkItems) { item ->
                HomeworkItemCard(
                    item = item,
                    onEditClick = { onHomeworkSelected(item) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        BottomTabBar(
            selectedScreen = Screen.NOTES,
            onScreenSelected = onNavigate
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckHomeworkScreenPreview() {
    CrmedumobileTheme {
        CheckHomeworkScreen(
            onNavigate = {},
            onBack = {},
            onHomeworkSelected = {}
        )
    }
}