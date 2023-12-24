package com.example.salonattask10.presentation.login.verify

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import com.example.salonattask10.presentation.common.OTPTextField
import com.example.salonattask10.presentation.common.VerifyTextFields
import com.example.salonattask10.presentation.navGrav.Route

@SuppressLint("SuspiciousIndentation")
@Composable
fun VerifyScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf(VerifyState()) }
    val viewmodel: VerifyViewModel = hiltViewModel()
    var verifyCode = remember { mutableStateOf("") }
    val handler: android.os.Handler = android.os.Handler()


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
            modifier = Modifier.padding(horizontal = 28.dp),
            onFilled = {
                verifyCode.value = it
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
                    val phoneNumber =
                        navController.previousBackStackEntry?.savedStateHandle?.get<String>("phone")
                    Log.i("verify", phoneNumber.toString())

                    if (verifyCode.value.length == 6 && phoneNumber != null) {
                        viewmodel.verify(phone = phoneNumber.toString(), code = "123456")

                        data.value = viewmodel.state.value
                        isLoading.value = true
                        handler.postDelayed(Runnable() {
                            if (data.value != null)
                                viewmodel.state.value.data?.let { response ->
                                    viewmodel.onEvent(
                                        token = response.token,
                                        centerId = response.data.id.toString()
                                    )
                                    navController.navigate(Route.HomeScreen.route)
                                }
                        }, 1000)

                    } else
                        Toast.makeText(
                            context,
                            "please enter valid code",
                            Toast.LENGTH_SHORT
                        ).show()
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