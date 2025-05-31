<<<<<<< HEAD
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
=======
package com.example.crmedumobile.presentation.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.theme.Purple
import com.example.crmedumobile.presentation.theme.Purple40
import com.example.crmedumobile.presentation.theme.PurpleGrey40
import androidx.core.net.toUri
import com.example.crmedumobile.presentation.theme.CrmEduMontserrat

@Composable
fun ScheduleItem(modifier: Modifier = Modifier, item: ScheduleModel) {
    val context = LocalContext.current
    Card(modifier = modifier.fillMaxWidth().padding(10.dp), colors = CardDefaults.cardColors(colorResource(R.color.schedaleBackgroundColor))) {
        Column(modifier.fillMaxWidth().padding(10.dp)) {
            Row(modifier.clip(
                RoundedCornerShape(15.dp)
            ).padding(0.5.dp)){
                Text(item.time, color = Black, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin, modifier = modifier.background(colorResource(R.color.light_green_very_light)), fontSize = 10.sp)
                Text(item.science, color = Black, fontSize = 16.sp, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin, modifier = modifier.background(colorResource(R.color.light_green_very_light)))
            }
            Row {
                Text(item.group, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin)
                Icon(painterResource(R.drawable.ic_video_call), null, tint = Purple40)
            }
            Text(String.format(context.getString(R.string.theme), item.theme), fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin)
            Text(item.teacher, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin)
            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){
                Text(stringResource(R.string.link), modifier.clickable{
                    val intent = Intent(Intent.ACTION_VIEW, item.link.toUri())
                    context.startActivity(intent)
                }, color = colorResource(R.color.linkColor))
            }
        }
    }
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
}