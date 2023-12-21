package com.example.salonattask10.presentation.login.login

import android.annotation.SuppressLint
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
import com.example.salonattask10.presentation.addServiceScreen.component.CustomButton
import com.example.salonattask10.presentation.addServiceScreen.component.TextField
import com.example.salonattask10.presentation.login.component.CircleProgressbar
import com.example.salonattask10.presentation.login.component.CustomHeader
import com.example.salonattask10.presentation.navGrav.Route

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val viewmodel: LoginViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomHeader(headerTitle = stringResource(id = R.string.login), onClick = {})


        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.please_enter),
            fontWeight = W600, fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        val phoneNumber = TextField(hint = R.string.ex_num, type = KeyboardType.Phone)
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
                    viewmodel.login("1211000009")
                    isLoading.value = true
                    if (viewmodel.state.value.data != null)
                        Toast.makeText(
                            context,
                            viewmodel.state.value.data?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    navigateTONext(navController)
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

fun navigateTONext(navController: NavHostController) {
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