package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class User() : Parcelable {

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

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        firebaseId = parcel.readString()
        phoneNumber = parcel.readString()
        email = parcel.readString()
        name = parcel.readString()
        v = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firebaseId)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
        parcel.writeString(name)
        parcel.writeValue(v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}