package com.example.salonattask10.presentation.login.verify

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.salonattask10.R
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.common.CircleProgressbar
import com.example.salonattask10.presentation.common.CustomHeader
import com.example.salonattask10.presentation.common.VerifyTextFields
import com.example.salonattask10.presentation.navGrav.Route

@SuppressLint("SuspiciousIndentation")
@Composable
fun VerifyScreen(
    navController: NavHostController,
    event: (AppEntryEvent) -> Unit,
) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf(VerifyState()) }
    val viewmodel: VerifyViewModel = hiltViewModel()
    var verifyCode by remember { mutableStateOf("") }
    val handler: android.os.Handler = android.os.Handler();


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomHeader(headerTitle = stringResource(id = R.string.verification),
            onClick = {
                navController.popBackStack()
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.pleas_enter_verify),
            fontWeight = FontWeight.W600, fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        VerifyTextFields(
            modifier = Modifier.padding(horizontal = 24.dp),
            onFilled = {
                verifyCode = it
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.resend),
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline
        )

        if (isLoading.value) {
            CircleProgressbar()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                label = stringResource(id = R.string.next),
                onClick = {
//                    if (verifyCode.length == 6) {
                    viewmodel.verify(phone = "1211000009", code = "123456")

                    data.value = viewmodel.state.value
                    isLoading.value = true
                    handler.postDelayed(Runnable() {
                        if (data.value != null)
                            viewmodel.state.value.data?.let {
                                event(AppEntryEvent.SaveAppEntry)
                                navController.navigate(Route.HomeScreen.route)
                            }
                    }, 1500)

//                    } else
//                        Toast.makeText(
//                            context,
//                            "please enter valid code",
//                            Toast.LENGTH_SHORT
//                        ).show()
                })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "",
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp
                )

                Text(
                    text = "",
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp
                )
            }
        }
    }
}