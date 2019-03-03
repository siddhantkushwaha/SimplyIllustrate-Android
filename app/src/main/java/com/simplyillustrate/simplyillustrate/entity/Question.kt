package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

class Question : Parcelable {

    @SerializedName("tags")
    var tags: List<String> = ArrayList()

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

    protected constructor(parcel: Parcel) {
        parcel.readList(this.tags, java.lang.String::class.java.classLoader)
        this.id = parcel.readValue(String::class.java.classLoader) as String
        this.createdBy = parcel.readValue(String::class.java.classLoader) as String
        this.title = parcel.readValue(String::class.java.classLoader) as String
        this.difficulty = parcel.readValue(String::class.java.classLoader) as String
        this.description = parcel.readValue(String::class.java.classLoader) as String
        this.timestamp = parcel.readValue(String::class.java.classLoader) as String
        this.v = parcel.readValue(Int::class.java.classLoader) as Int
    }

    constructor() {}

    override fun toString(): String {
        return "tags -> $tags users -> $createdBy difficulty -> $difficulty"
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(tags)
        dest.writeValue(id)
        dest.writeValue(createdBy)
        dest.writeValue(title)
        dest.writeValue(difficulty)
        dest.writeValue(description)
        dest.writeValue(timestamp)
        dest.writeValue(v)
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