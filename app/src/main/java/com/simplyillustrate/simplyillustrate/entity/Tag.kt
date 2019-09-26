package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Tag() : Parcelable {

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("use_count")
    var useCount: Int? = null

    @SerializedName("timestamp")
    var timestamp: Date? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        createdBy = parcel.readString()
        name = parcel.readString()
        useCount = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(createdBy)
        parcel.writeString(name)
        parcel.writeValue(useCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tag> {
        override fun createFromParcel(parcel: Parcel): Tag {
            return Tag(parcel)
        }

        override fun newArray(size: Int): Array<Tag?> {
            return arrayOfNulls(size)
        }
    }
}