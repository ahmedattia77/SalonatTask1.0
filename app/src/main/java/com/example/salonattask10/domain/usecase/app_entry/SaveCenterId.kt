package com.example.salonattask10.domain.usecase.app_entry

import com.example.salonattask10.domain.manger.LocalUserManger

class SaveCenterId(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke (centerID:String){
        localUserManger.saveCenterId(centerId = centerID)
    }
}