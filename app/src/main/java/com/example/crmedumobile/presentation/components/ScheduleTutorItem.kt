package com.example.crmedumobile.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.theme.BoldMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat24

@Composable
fun ScheduleTutorItem(
    item: ScheduleModel,
    lessonInfoClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
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

//    val borderColor = when (item.attendanceStatus) {
//        "Отсутствовал" -> Color.Red
//        "Присутствовал" -> Color.Green
//        "Уваж. причина" -> Color.Blue
//        else -> Color.Transparent
//    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
//            .border(2.dp, borderColor, RoundedCornerShape(18.dp))
            .background(Color(item.color.toColorInt()))
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
                text = item.type.toString(),
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
                    lessonInfoClick(item.id)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleItemTutorPreview() {
//    ScheduleTutorItem(
//        item = ScheduleModel(
//            id = 1L,
//            time = "10:00",
//            name = "Математика",
//            type = "Индивидуальное",
//            participant = "Иванов",
//            color = "0xFFBBDEFB",
//            date = "2025-05-05T10:00:00Z",
////            attendanceStatus = "Присутствовал"
//        ),
//        lessonInfoClick = {}
//    )
}