package com.example.salonattask10.domain.usecase.app_entry

import com.example.salonattask10.domain.manger.LocalUserManger

class ReadUserEntry(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke() = localUserManger.readAppEntry()
}