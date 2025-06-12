package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.domain.model.Notification
import com.example.crmedumobile.domain.model.NotificationType

@Composable
fun NotificationItem(modifier: Modifier = Modifier, notification: Notification) {
    val backgroundColor = when (notification.notificationType) {
        NotificationType.LESSON -> Color(0xFFE8E9FF)
        NotificationType.HOMEWORK -> Color(0xFFD9BDDB)
        NotificationType.PAYMENT -> Color(0xFF79E4FF)
        NotificationType.OTHER -> Color(0xFF79E4FF)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = notification.title,
                fontWeight = FontWeight.SemiBold
            )
            Row(modifier.fillMaxWidth().padding(top = 4.dp)) {
                if (notification.description.isNotEmpty()) {
                    Text(
                        text = notification.description,
                        color = Color.DarkGray,
                        modifier = modifier.fillMaxWidth().weight(1f)
                    )
                }

                if (notification.notificationType != NotificationType.PAYMENT) {
                    Text(
                        text = "Перейти",
                        color = Color.Blue,
                        fontSize = 14.sp,
                        modifier = modifier.weight(1f).fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}
