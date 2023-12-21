package com.example.salonattask10.domain.usecase.app_entry

import com.example.salonattask10.domain.manger.LocalUserManger

class SaveUserEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke (){
        localUserManger.saveAppEntry()
    }
}