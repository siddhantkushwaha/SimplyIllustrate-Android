package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PracticeQuestion() : Parcelable {

    @SerializedName("tags")
    var tags: List<String>? = null

    @SerializedName("incorrect_answers")
    var incorrectAnswers: List<String>? = null

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("difficulty")
    var difficulty: String? = null

    @SerializedName("question")
    var question: String? = null

    @SerializedName("correct_answer")
    var correctAnswer: String? = null

    @SerializedName("timestamp")
    var timestamp: String? = null

    @SerializedName("__v")
    var v: Int? = null

    constructor(parcel: Parcel) : this() {
        tags = parcel.createStringArrayList()
        incorrectAnswers = parcel.createStringArrayList()
        id = parcel.readString()
        createdBy = parcel.readString()
        type = parcel.readString()
        difficulty = parcel.readString()
        question = parcel.readString()
        correctAnswer = parcel.readString()
        timestamp = parcel.readString()
        v = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(tags)
        parcel.writeStringList(incorrectAnswers)
        parcel.writeString(id)
        parcel.writeString(createdBy)
        parcel.writeString(type)
        parcel.writeString(difficulty)
        parcel.writeString(question)
        parcel.writeString(correctAnswer)
        parcel.writeString(timestamp)
        parcel.writeValue(v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PracticeQuestion> {
        override fun createFromParcel(parcel: Parcel): PracticeQuestion {
            return PracticeQuestion(parcel)
        }

        override fun newArray(size: Int): Array<PracticeQuestion?> {
            return arrayOfNulls(size)
        }
    }
}
