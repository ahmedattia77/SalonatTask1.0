package com.example.salonattask10.presentation.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.salonattask10.R
import com.example.salonattask10.data.model.serviceJON.Data
import com.example.salonattask10.presentation.Dimens
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.common.SingleItem
import com.example.salonattask10.presentation.common.CircleProgressbar
import com.example.salonattask10.presentation.common.OTPTextField
import com.example.salonattask10.presentation.common.ShimmerEffect
import com.example.salonattask10.presentation.common.VerifyTextFields
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navigationToServiceScreen: () -> Unit,
    navigationToShowServiceScreen: (Data) -> Unit,
    navigationToAddServiceScreen: () -> Unit,
    list: List<Data>?
) {
    val con = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.NavigationButtonHeight2)
                .background(
                    colorResource(id = R.color.lightPink)
                )
        ) {
            val (home, notification) = createRefs()
            Text(
                text = "Home",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(home) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "not",
                modifier = Modifier.constrainAs(notification) {
                    end.linkTo(parent.end, margin = 12.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            text = "Pending Requests",
            fontWeight = FontWeight.W700,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(id = R.string.There_are))
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Your Service",
                fontWeight = FontWeight.W700,
                fontSize = 20.sp
            )
            TextButton(onClick = { navigationToServiceScreen() }) {
                Text(text = "see all", fontWeight = FontWeight.W700, fontSize = 16.sp)
            }
        }


        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {

            if (list == null)
                ShimmerEffect(repeat = 4)
            else if (list != null)
                list.let {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(items = it, itemContent = { item ->
                            SingleItem(item, onClick = { navigationToShowServiceScreen(item) })
                        })
                    }
                }
            else
                LaunchedEffect(Unit) {
                    delay(3000)
                    Toast.makeText(
                        con,
                        "There is no service , Add  one to show up",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            Spacer(modifier = Modifier.height(20.dp))

        }

        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(label = "Add Service", onClick = { navigationToAddServiceScreen() })
    }


}