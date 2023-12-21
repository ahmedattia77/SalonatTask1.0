package com.example.salonattask10.presentation.addServiceScreen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R


@Composable
fun AddServiceCard(
    radioBtLabel: String,
    checkBoxLabel: String,
    checkBoxLabel2: String,
    onCheckBox: (Boolean) -> Unit,
    onCheckBox2: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .border(
                border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        RadioButtonComponent(text = radioBtLabel, onSelect = {/*TODO*/ })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(hint = R.string.main_price, type = KeyboardType.Number)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(hint = R.string.no_person, type = KeyboardType.Number)
        Spacer(modifier = Modifier.height(8.dp))
        Checkbox(text = checkBoxLabel,
            onClick = {onCheckBox(it)})
        Spacer(modifier = Modifier.height(8.dp))
        Checkbox(text = checkBoxLabel2,
            onClick = {onCheckBox2(it)})
        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Composable
fun AddServiceCard(
    radioBtLabel: String,
    checkBoxLabel: String,
    onCheckBox: (Boolean) -> Unit ,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .border(
                border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        RadioButtonComponent(text = radioBtLabel, onSelect = {/*TODO*/ })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(hint = R.string.main_price, type = KeyboardType.Number)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(hint = R.string.no_person, type = KeyboardType.Number)
        Spacer(modifier = Modifier.height(8.dp))
        Checkbox(
            text = checkBoxLabel,
            onClick = {onCheckBox(it)}
        )
    }

}