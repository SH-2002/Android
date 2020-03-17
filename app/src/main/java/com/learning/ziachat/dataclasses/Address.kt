package com.learning.ziachat.dataclasses


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    val geo: Geo,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
) : Serializable