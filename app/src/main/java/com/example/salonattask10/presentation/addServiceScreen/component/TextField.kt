package com.example.salonattask10.presentation.addServiceScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    hint: Int, type: KeyboardType ,

) : String {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textValue.value,
        onValueChange = { text ->
            textValue.value = text
        },
        label = { Text(text = stringResource(id = hint)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .padding(horizontal = 25.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = colorResource(id = R.color.darkPink),
            focusedBorderColor = Color.Gray,
            textColor = Color.Black ,
            cursorColor = colorResource(id = R.color.darkPink)
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
    )
    return textValue.value
}