package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.HomeworkItemCard
import com.example.crmedumobile.presentation.theme.CrmedumobileTheme
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Gray
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import kotlinx.serialization.Serializable

@Serializable
data class HomeworkItem(
    val id: Long,
    val studentName: String,
    val topic: String,
    val status: String
)

@Composable
fun CheckHomeworkScreen(
    navController: NavHostController,
    onBack: () -> Unit = {},
) {
    val homeworkItems = listOf(
        HomeworkItem(1, "Кирилл Рогачёв", "Подобные треугольники", "Непроверено"),
        HomeworkItem(2, "Анна Смирнова", "Квадратные уравнения", "Проверено"),
        HomeworkItem(3, "Иван Петров", "Векторы", "Непроверено")
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
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DarkPurple
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


        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DarkPurple
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
                    onEditClick = {
                        navController.navigate("homework/${item.id}")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckHomeworkScreenPreview() {
    CrmedumobileTheme {
//        CheckHomeworkScreen(
//            onBack = {},
//            onHomeworkSelected = {}
//        )
    }
}