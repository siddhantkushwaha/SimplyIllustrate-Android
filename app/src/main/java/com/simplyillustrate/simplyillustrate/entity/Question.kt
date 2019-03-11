package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Question() : Parcelable {

    @SerializedName("tags")
    var tags: ArrayList<String> = ArrayList()

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("difficulty")
    var difficulty: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("timestamp")
    var timestamp: String? = null

    @SerializedName("__v")
    var v: Int? = null

    constructor(parcel: Parcel) : this() {
        tags = parcel.createStringArrayList() ?: ArrayList()
        id = parcel.readString()
        createdBy = parcel.readString()
        title = parcel.readString()
        difficulty = parcel.readString()
        description = parcel.readString()
        timestamp = parcel.readString()
        v = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(tags)
        parcel.writeString(id)
        parcel.writeString(createdBy)
        parcel.writeString(title)
        parcel.writeString(difficulty)
        parcel.writeString(description)
        parcel.writeString(timestamp)
        parcel.writeValue(v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}