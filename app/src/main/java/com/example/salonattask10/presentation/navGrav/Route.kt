package com.example.salonattask10.presentation.navGrav


sealed class Route(val route : String) {

    object LoginScreen : Route(route = "loginScreen")
    object VerifyScreen : Route(route = "verifyScreen")
    object HomeScreen : Route(route = "homeScreen")
    object Service : Route(route = "serviceScreen")
    object AddService : Route(route = "AddServiceScreen")
    object ShowService : Route(route = "ShowServiceScreen")

}