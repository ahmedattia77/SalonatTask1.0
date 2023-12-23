package com.example.salonattask10

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.salonattask10.domain.usecase.app_entry.AppEntryUseCases
import com.example.salonattask10.presentation.navGrav.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _appEntryCheck = mutableStateOf(true)
    val appEntryCheck: State<Boolean> = _appEntryCheck

    private val _startDestination = mutableStateOf(Route.LoginScreen.route)
    val startDestination: State<String> = _startDestination


    init {
        appEntryUseCases.readUserEntry().onEach {
            if (it)
                _startDestination.value = Route.HomeScreen.route
            else
                _startDestination.value = Route.LoginScreen.route

            delay(400)
            _appEntryCheck.value = false

        }.launchIn(viewModelScope)

        getCenterId()
        getToken()

    }

    private fun getCenterId() {
        appEntryUseCases.readCenterId().onEach {
           Constants.LOCAL_CENTER_ID = it
        }.launchIn(viewModelScope)
    }

    private fun getToken() {
        appEntryUseCases.readToken().onEach {
            Constants.LOCAL_TOKEN = it
        }.launchIn(viewModelScope)
    }

}