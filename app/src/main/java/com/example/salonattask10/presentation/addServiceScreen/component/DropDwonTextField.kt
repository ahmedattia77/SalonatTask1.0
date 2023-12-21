package com.example.salonattask10.presentation.addServiceScreen.component

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R
import com.example.salonattask10.data.model.categoriesJON.Data

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    list: List<Data>? = null, hint: Int,
    onClick: (Data) -> Unit,
) {
//    var options = listOf("empty")
//
//    if (list == null)
//        options = listOf("empty")
//    else
//        options = list


    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text(stringResource(id = hint)) },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrowdwon),
                        "contentDescription"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = colorResource(id = R.color.darkPink),
                    focusedBorderColor = Color.Gray,
                    textColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }, modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                list?.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption.name) },
                        onClick = {
                            selectedOptionText = selectionOption.name
                            expanded = false
                            onClick(selectionOption)
                        })
                }
            }
        }
    }
}