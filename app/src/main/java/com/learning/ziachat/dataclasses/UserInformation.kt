package com.learning.ziachat.dataclasses


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.io.Serializable

data class UserInformation(
    @SerializedName("address")
    val address: Address,
    @SerializedName("company")
    val company: Company,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
) : Serializable {

    interface ApiCall{

        companion object{
            val BASE_URL = "https://jsonplaceholder.typicode.com/"
        }

        @GET("users")
        fun getInformation() : Call<List<UserInformation>>
    }

}