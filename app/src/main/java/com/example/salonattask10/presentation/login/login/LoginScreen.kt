package com.example.salonattask10.presentation.login.login

import android.annotation.SuppressLint
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.salonattask10.R
import com.example.salonattask10.presentation.addServiceScreen.component.CenterTopBar
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.addServiceScreen.component.TextField
import com.example.salonattask10.presentation.common.CircleProgressbar
import com.example.salonattask10.presentation.common.CustomHeader
import com.example.salonattask10.presentation.common.OTPTextField
import com.example.salonattask10.presentation.navGrav.Route

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val inputPhone = remember { mutableStateOf("") }
    val viewmodel: LoginViewModel = hiltViewModel()
    val handler: android.os.Handler = android.os.Handler()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CenterTopBar(title = "Login" , ){}


        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.please_enter),
            fontWeight = W600, fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        inputPhone.value = TextField(hint = R.string.ex_num, type = KeyboardType.Phone)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.do_not_have),
                fontWeight = W600,
                fontSize = 14.sp
            )
            TextButton(onClick = {
                Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    fontWeight = W600,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        if (isLoading.value) {
            CircleProgressbar()
        }


        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp)
        ) {

            CustomButton(
                label = stringResource(id = R.string.next), onClick = {
                    ///0106778411
                    if (inputPhone.value != "") {
                        isLoading.value = true
                        viewmodel.login(phone = inputPhone.value)
                        Log.i("login", inputPhone.value)
                        handler.postDelayed(Runnable() {
                            if (viewmodel.stateLogin.value.data != null) {
                                Toast.makeText(
                                    context,
                                    viewmodel.stateLogin.value.data?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            if (viewmodel.stateLogin.value.data?.status == true)
                                navigateTONext(navController, phone = inputPhone.value)
                            else {
                                isLoading.value = false
                                Toast.makeText(
                                    context,
                                    R.string.enterValid,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }, 1500)
                    } else Toast.makeText(
                        context,
                        R.string.enterValid,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )

            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.iam_agree),
                    fontWeight = W600,
                    fontSize = 14.sp
                )
                TextButton(onClick = {
                    Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT)
                        .show()
                }) {
                    Text(
                        text = stringResource(id = R.string.terms_conditions),
                        fontWeight = W600,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}

fun navigateTONext(navController: NavHostController, phone: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "phone",
        value = phone
    )
    navController.navigate(Route.VerifyScreen.route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}