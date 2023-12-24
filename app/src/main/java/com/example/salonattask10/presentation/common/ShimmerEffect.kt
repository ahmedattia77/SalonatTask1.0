package com.example.salonattask10.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerEffect(repeat:Int) {
    Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
        repeat(repeat) {
            SingleItemShimmerEffect()
        }
    }
}