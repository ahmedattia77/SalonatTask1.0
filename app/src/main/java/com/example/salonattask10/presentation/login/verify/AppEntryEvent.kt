package com.example.salonattask10.presentation.login.verify

sealed class AppEntryEvent {
    object SaveAppEntry : AppEntryEvent()
    companion object {
        var token:String = ""
        var centerId:String = ""
    }
}
