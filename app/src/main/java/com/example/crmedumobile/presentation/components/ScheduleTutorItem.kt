package com.example.crmedumobile.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.states.forNotificationScheduler.ScheduleItemData
import com.example.crmedumobile.presentation.theme.*

@Composable
fun ScheduleItem(
    item: ScheduleItemData,
    modifier: Modifier = Modifier,
    onEditClick: (ScheduleItemData) -> Unit = {}
) {
    var isAnimating by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isAnimating) 360f else 0f,
        animationSpec = tween(durationMillis = 600),
        finishedListener = { isAnimating = false }
    )
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    // Определяем цвет обводки на основе статуса посещаемости
    val borderColor = when (item.attendanceStatus) {
        "Отсутствовал" -> Color.Red
        "Присутствовал" -> Color.Green
        "Уваж. причина" -> Color.Blue
        else -> Color.Transparent // На случай некорректного статуса
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(2.dp, borderColor, RoundedCornerShape(18.dp))
            .background(item.color)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${item.time} ${item.name}",
                style = RegularMontserrat24,
                color = Color.Black
            )
            Text(
                text = item.type,
                style = RegularMontserrat16,
                color = Color.Black
            )
            Text(
                text = item.participant,
                style = BoldMontserrat16,
                color = Color.Black
            )
        }
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit Schedule Item",
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .padding(end = 8.dp)
                .scale(scale)
                .rotate(rotation)
                .clickable {
                    isAnimating = true
                    onEditClick(item)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleItemPreview() {
    ScheduleItem(
        item = ScheduleItemData(
            id = "preview_1",
            time = "10:00",
            name = "Математика",
            type = "Индивидуальное",
            participant = "Иванов",
            color = Color(0xFFBBDEFB),
            dateTime = "2025-05-05T10:00:00Z",
            attendanceStatus = "Присутствовал"
        ),
        onEditClick = {}
    )
}