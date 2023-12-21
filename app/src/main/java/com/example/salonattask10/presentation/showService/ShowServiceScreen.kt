package com.example.salonattask10.presentation.showService

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.salonattask10.R
import com.example.salonattask10.data.model.services_detailsJON.Data
import com.example.salonattask10.presentation.login.component.CustomHeader

@Composable
fun ShowServiceScreen(
    list: List<Data>?,
    serviceName: String = "",
    serviceID: Int?,
    navigateBack: () -> Unit
) {

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        CustomHeader(headerTitle = serviceName.toString(), onClick = { navigateBack() })

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Prices",
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
            )
            val viewmodel: ShowServiceViewModel = hiltViewModel()
            TextButton(
                onClick = {
                    if (serviceID != null)
                        viewmodel.deleteService(centerId = 123, serviceId = serviceID)
                    viewmodel.stateDelete.value.data?.message?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                    navigateBack()
                }) {
                Text(
                    text = "Delete Service",
                    fontWeight = FontWeight.W700, fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        list?.let {
            list.forEach { item ->
                ServiceContainer(item = item)
            }
        }

    }
}

@Composable
fun ServiceContainer(item: Data) {
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
        CustomRow(serviceName = item.name)
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

@Composable
fun CustomRow(serviceName: String) {
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
            onClick = {}) {
            Text(
                text = "Delete",
                fontWeight = FontWeight.W700, fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
            )
        }

    }
    Spacer(modifier = Modifier.height(10.dp))
}

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
fun CustomLine() {
    Divider(
        color = colorResource(id = R.color.lightPink),
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}