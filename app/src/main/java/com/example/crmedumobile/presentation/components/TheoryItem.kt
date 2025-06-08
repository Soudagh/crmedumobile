package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.TheoryModel
import com.example.crmedumobile.presentation.theme.CrmEduMontserrat
import com.example.crmedumobile.presentation.theme.Purple40

@Composable
fun TheoryItem(modifier: Modifier = Modifier, theoryModel: TheoryModel) {
    Box(modifier.fillMaxWidth()){
        Column(modifier.fillMaxWidth().padding(15.dp)) {
            Row {
                Text(String.format(stringResource(R.string.task), theoryModel.number), modifier = Modifier.weight(1f), textAlign = TextAlign.Start, color = Purple40, fontSize = 20.sp, fontFamily = CrmEduMontserrat, fontWeight = FontWeight.Bold)
                Text(String.format(stringResource(R.string.score), theoryModel.score), modifier = Modifier.weight(1f), textAlign = TextAlign.End, fontFamily = CrmEduMontserrat, color = Black)
            }
            Image(painterResource(theoryModel.image), null, modifier = modifier.fillMaxWidth().height(80.dp), contentScale = ContentScale.Crop)
            Row {
                Text(stringResource(R.string.answer), modifier = modifier.weight(5f), textAlign = TextAlign.Start, fontSize = 17.sp, fontFamily = CrmEduMontserrat, color = Black, fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.attachedFile), modifier = modifier.weight(7f), textAlign = TextAlign.End, fontFamily = CrmEduMontserrat, color = Black)
            }
        }
    }
}