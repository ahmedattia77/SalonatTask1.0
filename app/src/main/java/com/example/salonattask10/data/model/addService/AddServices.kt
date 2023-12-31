package com.example.salonattask10.data.model.addService

import com.google.gson.annotations.SerializedName

data class AddServices(
    @SerializedName("type_id") var typeId: Int,
    @SerializedName("price") var price: String,
    @SerializedName("addition") var addition: Int,
    @SerializedName("no_person") var noPerson: String,
    @SerializedName("offer_price") var offerPrice: Int?,
    @SerializedName("from") var from: String?,
    @SerializedName("to") var to: String?
)