package com.example.salonattask10.presentation.showService.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R
import com.example.salonattask10.data.model.services_detailsJON.Data

@Composable
fun ServiceContainer(
    item: Data,
    onClick: (Data) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .border(
                border = BorderStroke(width = 1.dp, colorResource(id = R.color.lightPink)),
                shape = RoundedCornerShape(12.dp)
            )
    )
    {
        CustomRow(serviceName = item.type_name , onclickDelete = {onClick(item)})
        CustomLine()
        CustomRow(leftSide = "Main Price", rightSide = item.price.toString())
        CustomLine()
        CustomRow(leftSide = "Available Workers", rightSide = item.no_person.toString())
        CustomLine()
        CustomRow(leftSide = "Additions", rightSide = item.addition.toString())
        CustomLine()
    }
    Spacer(modifier = Modifier.height(20.dp))
}