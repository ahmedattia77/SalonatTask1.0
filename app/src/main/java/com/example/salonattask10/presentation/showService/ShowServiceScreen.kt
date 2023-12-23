package com.example.salonattask10.presentation.showService

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.salonattask10.Constants
import com.example.salonattask10.data.model.services_detailsJON.Data
import com.example.salonattask10.presentation.common.CustomHeader
import com.example.salonattask10.presentation.showService.component.ServiceContainer

@Composable
fun ShowServiceScreen(
    list: List<Data>?,
    serviceName: String = "",
    serviceID: Int?,
    navigateBack: () -> Unit,
    refresh: () -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        CustomHeader(headerTitle = serviceName, onClick = {
            navController.popBackStack()
            Log.i("showServiceBack", "clicked")
        })

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
                        viewmodel.deleteService(
                            centerId = Constants.LOCAL_CENTER_ID.toInt(),
                            serviceId = serviceID
                        )
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
        val viewmodel: ShowServiceViewModel = hiltViewModel()

        list?.let {
            list.forEach { item ->
                ServiceContainer(item = item, onClick = {
                    viewmodel.deleteServiceType(
                        serviceId = serviceID ?: 0,
                        centerId = Constants.LOCAL_CENTER_ID.toInt(),
                        serviceTypeId = it.type_id
                    )
                    viewmodel.stateDeleteServiceType.value.data?.let {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                    refresh()
                })
            }
        }
    }
}