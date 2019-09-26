package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Answer() : Parcelable {

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: User? = null

    @SerializedName("question_id")
    var questionId: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("timestamp")
    var timestamp: Date? = null

    @SerializedName("is_accepted")
    var isAccepted: Boolean? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        createdBy = parcel.readParcelable(User::class.java.classLoader)
        questionId = parcel.readString()
        text = parcel.readString()
        timestamp = parcel.readSerializable() as? Date
        isAccepted = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(createdBy, flags)
        parcel.writeString(questionId)
        parcel.writeString(text)
        parcel.writeSerializable(timestamp)
        parcel.writeValue(isAccepted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Answer> {
        override fun createFromParcel(parcel: Parcel): Answer {
            return Answer(parcel)
        }

        override fun newArray(size: Int): Array<Answer?> {
            return arrayOfNulls(size)
        }
    }
}