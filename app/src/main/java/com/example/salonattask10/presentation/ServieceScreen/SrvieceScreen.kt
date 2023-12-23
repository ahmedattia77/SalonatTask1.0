package com.example.salonattask10.presentation.ServieceScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.common.SingleItem
import com.example.salonattask10.presentation.common.CircleProgressbar
import com.example.salonattask10.presentation.common.CustomHeader

@Composable
fun ServiceScreen(
    navigationToAddServiceScreen: () -> Unit,
    navigationToHomeScreen: () -> Unit,
    navigationToShowServiceScreen: (Data) -> Unit,
    list: List<Data>? ,
    navController:NavHostController
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        CustomHeader(
            headerTitle = "Service",
            onClick = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(20.dp))

        if (list == null)
            CircleProgressbar()
        else
            list?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)

                ) {
                    items(items = list, itemContent = { item ->
                        SingleItem(item, onClick = {
                            navigationToShowServiceScreen(it)
                        })
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            CustomButton(label = "Add Service", onClick = { navigationToAddServiceScreen() })
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}