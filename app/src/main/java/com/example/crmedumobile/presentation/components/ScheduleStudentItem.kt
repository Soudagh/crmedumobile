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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.theme.CrmEduMontserrat
import com.example.crmedumobile.presentation.theme.Purple40

@Composable
fun ScheduleStudentItem(modifier: Modifier = Modifier, item: ScheduleModel) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.schedaleBackgroundColor))
    ) {
        Column(modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Row(
                modifier
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
                    .padding(0.5.dp)
            ) {
                Text(
                    item.time,
                    color = Black,
                    fontFamily = CrmEduMontserrat,
                    fontWeight = FontWeight.Thin,
                    modifier = modifier.background(colorResource(R.color.light_green_very_light)),
                    fontSize = 10.sp
                )
                Text(
                    item.name,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = CrmEduMontserrat,
                    fontWeight = FontWeight.Thin,
                    modifier = modifier.background(colorResource(R.color.light_green_very_light))
                )
            }
            Row {
                Text(item.participant, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin)
                Icon(painterResource(R.drawable.ic_video_call), null, tint = Purple40)
            }
            Text(item.teacher!!, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Thin)
            Box(modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Text(stringResource(R.string.link), modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, item.link?.toUri())
                    context.startActivity(intent)
                }, color = colorResource(R.color.linkColor))
            }
        }
    }
}