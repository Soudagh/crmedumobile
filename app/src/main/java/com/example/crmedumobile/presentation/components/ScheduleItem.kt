package com.example.crmedumobile.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import com.example.crmedumobile.presentation.theme.BoldMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat24

@Composable
fun ScheduleItem(
    time: String,
    name: String,
    type: String,
    participant: String,
    color: Color,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    var isAnimating by remember { mutableStateOf(false) }

    // Анимация вращения и масштабирования
    val rotation by animateFloatAsState(
        targetValue = if (isAnimating) 360f else 0f,
        animationSpec = tween(durationMillis = 600),
        finishedListener = { isAnimating = false }
    )
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(color)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "$time $name",
                style = RegularMontserrat24,
                color = Color.Black
            )
            Text(
                text = type,
                style = RegularMontserrat16,
                color = Color.Black
            )
            Text(
                text = participant,
                style = BoldMontserrat16,
                color = Color.Black
            )
        }
        Icon(
            imageVector = Icons.Filled.Edit, // Стандартная иконка Android
            contentDescription = "Edit Schedule Item",
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .padding(end = 8.dp)
                .scale(scale)
                .rotate(rotation)
                .clickable {
                    isAnimating = true
                    // TODO: Реализовать изменение расписания
                    onEditClick()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleItemPreview() {
    ScheduleItem(
        time = "10:00",
        name = "Математика",
        type = "Индивидуальное",
        participant = "Иванов",
        color = Color(0xFFBBDEFB),
        onEditClick = {}
    )
}