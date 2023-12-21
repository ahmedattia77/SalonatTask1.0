package com.example.salonattask10.domain.usecase.app_entry

data class AppEntryUseCases(
    val readUserEntry: ReadUserEntry,
    val saveUserEntry: SaveUserEntry,
    val saveToken: SaveToken,
    val readToken: ReadToken,
    val saveCenterId: SaveCenterId,
    val readCenterId: ReadCenterId
)
