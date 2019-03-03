package com.simplyillustrate.simplyillustrate.entity

import com.google.gson.annotations.SerializedName


class User {

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("firebase_id")
    var firebaseId: String? = null

    @SerializedName("phone_number")
    var phoneNumber: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("__v")
    var v: Int? = null
}