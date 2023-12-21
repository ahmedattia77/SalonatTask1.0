package com.example.salonattask10.data.model.addService

import com.google.gson.annotations.SerializedName

data class AddServiceInput(
    @SerializedName("center_id") var centerId: Int,
    @SerializedName("service_id") var serviceId: Int,
    @SerializedName("services") var services: ArrayList<AddServices>
)