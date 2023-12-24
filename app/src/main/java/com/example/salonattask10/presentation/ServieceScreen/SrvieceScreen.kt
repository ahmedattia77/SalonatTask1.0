package com.example.salonattask10.presentation.ServieceScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data
import com.example.salonattask10.presentation.addServiceScreen.component.CenterTopBar
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.common.SingleItem
import com.example.salonattask10.presentation.common.CircleProgressbar
import com.example.salonattask10.presentation.common.CustomHeader
import com.example.salonattask10.presentation.common.ShimmerEffect
import kotlinx.coroutines.delay

@Composable
fun ServiceScreen(
    navigationToAddServiceScreen: () -> Unit,
    navigationToShowServiceScreen: (Data) -> Unit,
    list: List<Data>?,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top
    ) {
//        CustomHeader(
//            headerTitle = "Service",
//            onClick = { navController.popBackStack() })

        CenterTopBar(title = "Service") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {

            if (list == null)
                ShimmerEffect(repeat = 6)
            else if (list != null) {
                list.let {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        items(items = list, itemContent = { item ->
                            SingleItem(item, onClick = {
                                navigationToShowServiceScreen(it)
                            })
                        })
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                LaunchedEffect(Unit) {
                    delay(3000)
                    Toast.makeText(
                        context,
                        "There is no service , Add  one to show up",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(label = "Add Service", onClick = { navigationToAddServiceScreen() })
        Spacer(modifier = Modifier.height(20.dp))
    }

}