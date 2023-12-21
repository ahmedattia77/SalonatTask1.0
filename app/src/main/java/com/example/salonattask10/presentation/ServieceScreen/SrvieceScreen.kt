package com.example.salonattask10.presentation.ServieceScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.homeScreen.component.SingleItem
import com.example.salonattask10.presentation.login.component.CircleProgressbar
import com.example.salonattask10.presentation.login.component.CustomHeader

@Composable
fun ServiceScreen(
    navigationToAddServiceScreen: () -> Unit,
    navigationToHomeScreen: () -> Unit,
    list: List<Data>?
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        CustomHeader(
            headerTitle = R.string.service.toString(),
            onClick = { navigationToHomeScreen() })

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
                        SingleItem(item , onClick = {
                            Log.i("singleItem" , it.category_id.toString())
                            Log.i("singleItem" , it.id.toString())
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