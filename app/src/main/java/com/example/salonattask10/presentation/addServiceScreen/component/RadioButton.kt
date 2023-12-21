package com.example.salonattask10.presentation.addServiceScreen.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R


@Composable
fun RadioButtonComponent(text: String, onSelect: (Boolean) -> Unit) {


    var isSelected by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) ,
        horizontalArrangement = Arrangement.Start ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // below line is use to get context.
        val context = LocalContext.current
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor =  colorResource(id = R.color.darkPink)
            ),
            selected = isSelected,
            modifier = Modifier.padding(all = Dp(value = 0F)),
            onClick = {
                isSelected = !isSelected
                onSelect(isSelected)
            }
        )

        Text(
            text = text,
            fontWeight = FontWeight.W800
        )
    }
}
