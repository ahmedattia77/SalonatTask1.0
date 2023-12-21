package com.example.salonattask10.presentation.addServiceScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.salonattask10.R

@Composable
fun Checkbox(
    text: String,
    onClick: (Boolean) -> Unit
) {
    val checked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { isChecked ->
                checked.value = isChecked
                onClick(checked.value)
            },
            colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.darkPink))
        )
        Text(text, fontWeight = FontWeight.W400)
    }
}