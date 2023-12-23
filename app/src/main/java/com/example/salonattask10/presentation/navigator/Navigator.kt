package com.example.salonattask10.presentation.navigator

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.salonattask10.R
import com.example.salonattask10.presentation.navGrav.Route
import com.example.salonattask10.presentation.ServieceScreen.ServiceScreen
import com.example.salonattask10.presentation.addServiceScreen.AddServiceScreen
import com.example.salonattask10.presentation.addServiceScreen.AddServiceViewModel
import com.example.salonattask10.presentation.homeScreen.HomeScreen
import com.example.salonattask10.presentation.homeScreen.HomeViewModel
import com.example.salonattask10.presentation.navigator.component.ButtonNavigation
import com.example.salonattask10.presentation.navigator.component.NavigationItem
import com.example.salonattask10.presentation.showService.ShowServiceScreen
import com.example.salonattask10.presentation.showService.ShowServiceViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator() {
    val con = LocalContext.current
    val serviceName = remember { mutableStateOf("") }
    val serviceId = remember { mutableIntStateOf(0) }

    val bottomNavigationItems = remember {
        listOf(
            NavigationItem(icon = R.drawable.home, title = "Home"),
            NavigationItem(icon = R.drawable.service, title = "Reservation"),
            NavigationItem(icon = R.drawable.service, title = "Service"),
            NavigationItem(icon = R.drawable.service, title = "Offers"),
            NavigationItem(icon = R.drawable.service, title = "Settings")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectItem by rememberSaveable {
        mutableStateOf(0)
    }

    val navigateVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.Service.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            ButtonNavigation(
                items = bottomNavigationItems,
                selectedItem = selectItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigationTo(
                            navController = navController,
                            rout = Route.HomeScreen.route
                        )

                        2 -> navigationTo(
                            navController = navController,
                            rout = Route.Service.route
                        )

                        else -> Toast.makeText(con, "not implemented yet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )
        }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewmodel: HomeViewModel = hiltViewModel()
                val data = viewmodel.state.value
                HomeScreen(
                    navigationToServiceScreen = {
                        navigationTo(navController, rout = Route.Service.route)
                    },
                    navigationToAddServiceScreen = {
                        navigationTo(navController, rout = Route.AddService.route)
                    },
                    navigationToShowServiceScreen = {
                        serviceName.value = it.name
                        serviceId.intValue = it.id
                        navigationTo(navController, rout = Route.ShowService.route)
                    },
                    list = data.data?.data
                )
            }

            composable(route = Route.Service.route) {
                val viewmodel: HomeViewModel = hiltViewModel()
                val data = viewmodel.state.value.data?.data
                data?.let {
                    it.forEach {
                        Log.i("service", "service_id : " + it.id.toString())
                    }
                }
                ServiceScreen(
                    navigationToAddServiceScreen = {},
                    navigationToHomeScreen = {},
                    navigationToShowServiceScreen = {
                        serviceName.value = it.name
                        serviceId.intValue = it.id
                        navigationTo(navController, rout = Route.ShowService.route)
                    },
                    list = data
                )
            }
            composable(route = Route.AddService.route) {
                val viewModel: AddServiceViewModel = hiltViewModel()
                val category = viewModel.categoryState.value.data?.data
                AddServiceScreen(categoryList = category, navigateBack = {
                    navigationTo(navController, Route.HomeScreen.route)
                })
            }

            composable(route = Route.ShowService.route) {
                val viewmodel: ShowServiceViewModel = hiltViewModel()
                viewmodel.getService(123, serviceId.value)
                val data = viewmodel.state.value.data?.data
                ShowServiceScreen(
                    list = data,
                    serviceName.value,
                    serviceId.intValue,
                    navigateBack = { navigationTo(navController, Route.HomeScreen.route) })
            }
        }
    }
}

fun navigationTo(navController: NavController, rout: String) {

    navController.navigate(rout) {
//        navController.graph.startDestinationRoute?.let {
//            popUpTo(it) {
//                saveState = false
//            }
//            launchSingleTop = false
//            restoreState = true
//        }
    }
}