package com.example.salonattask10.presentation.addServiceScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R

@Composable
fun CustomButton(label: String, onClick: (Unit) -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.darkPink)),
        onClick = { onClick(Unit) }) {
        Text(text = label, color = Color.White)
    }
}