package com.example.crmedumobile.presentation.components

import LocalDimensions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.screen.HomeworkItem
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat24
import com.example.crmedumobile.presentation.theme.SubjectColors

@Composable
fun HomeworkItemCard(
    item: HomeworkItem,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onEditClick() }, // Make the entire card clickable
        shape = RoundedCornerShape(LocalDimensions.current.cardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = SubjectColors[0]),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp)
                .padding(LocalDimensions.current.textCardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.studentName,
                    style = RegularMontserrat24,
                    color = Color.Black
                )
                Text(
                    text = "Тема: ${item.topic}",
                    style = RegularMontserrat16,
                    color = Color.Black
                )
                Text(
                    text = "Статус: ${item.status}",
                    style = RegularMontserrat16,
                    color = Color.Black
                )
            }
        }
    }
}