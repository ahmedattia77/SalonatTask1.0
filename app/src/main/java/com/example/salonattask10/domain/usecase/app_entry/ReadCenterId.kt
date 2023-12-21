package com.example.salonattask10.domain.usecase.app_entry

import com.example.salonattask10.domain.manger.LocalUserManger

class ReadCenterId(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke() = localUserManger.readCenterId()
}