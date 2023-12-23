package com.example.salonattask10.presentation.showService.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomRow(leftSide: String, rightSide: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftSide,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp
        )

        val _rightSide = if (rightSide == "true") "Yes"
        else if (rightSide == "false") "No"
        else rightSide

        Text(
            text = _rightSide,
            fontWeight = FontWeight.W700, fontSize = 18.sp,
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CustomRow(
    serviceName: String,
    onclickDelete: () -> Unit
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$serviceName Details",
            fontWeight = FontWeight.W600,
            fontSize = 20.sp
        )
        TextButton(
            onClick = {onclickDelete ()}) {
            Text(
                text = "Delete",
                fontWeight = FontWeight.W700, fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}
