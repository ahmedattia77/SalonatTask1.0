package com.example.salonattask10.presentation.navGrav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.salonattask10.presentation.login.login.LoginScreen
import com.example.salonattask10.presentation.login.verify.AppEntryEvent
import com.example.salonattask10.presentation.login.verify.VerifyScreen
import com.example.salonattask10.presentation.login.verify.VerifyViewModel
import com.example.salonattask10.presentation.navigator.Navigator

@Composable
fun NavGrav(startDes :String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDes) {
        composable(route = Route.HomeScreen.route) {
            Navigator()
        }
        composable(route = Route.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = Route.VerifyScreen.route) {
            val viewModel: VerifyViewModel = hiltViewModel()
            VerifyScreen(navController, event = { viewModel.onEvent(it , "" ,"")})
        }
    }
}
