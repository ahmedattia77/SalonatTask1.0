package com.example.salonattask10.presentation.addServiceScreen

data class ServiceField_(
    var isSelected: Boolean,
    var isAdditionSelected: Boolean,
    var price: String,
    var personNo: String,
)
fun isSelected (isSelected:Boolean) : Boolean{
    return isSelected == true
}
