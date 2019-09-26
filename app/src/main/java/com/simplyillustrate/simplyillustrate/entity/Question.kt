package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.simplyillustrate.simplyillustrate.SimplyIllustrate
import java.util.*
import kotlin.collections.ArrayList

class Question() : Parcelable {

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: User? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("tags")
    var tags: ArrayList<Tag> = ArrayList()

    @SerializedName("difficulty")
    var difficulty: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("timestamp")
    var timestamp: Date? = null

    @SerializedName("__v")
    var v: Int? = null

    fun getTagNames(): List<String> {
        return tags.map { tag ->
            tag.name ?: "no_name"
        }
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        createdBy = parcel.readParcelable(User::class.java.classLoader)
        title = parcel.readString()
        difficulty = parcel.readString()
        description = parcel.readString()
        timestamp = parcel.readSerializable() as? Date
        v = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(createdBy, flags)
        parcel.writeString(title)
        parcel.writeString(difficulty)
        parcel.writeString(description)
        parcel.writeSerializable(timestamp)
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