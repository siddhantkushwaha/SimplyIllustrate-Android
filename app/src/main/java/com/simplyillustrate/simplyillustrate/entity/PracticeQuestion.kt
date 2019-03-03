package com.simplyillustrate.simplyillustrate.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PracticeQuestion : Parcelable {

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

    protected constructor(parcel: Parcel) {
        parcel.readList(this.tags, java.lang.String::class.java.classLoader)
        parcel.readList(this.incorrectAnswers, java.lang.String::class.java.classLoader)
        this.id = parcel.readValue(String::class.java.classLoader) as String
        this.createdBy = parcel.readValue(String::class.java.classLoader) as String
        this.type = parcel.readValue(String::class.java.classLoader) as String
        this.difficulty = parcel.readValue(String::class.java.classLoader) as String
        this.question = parcel.readValue(String::class.java.classLoader) as String
        this.correctAnswer = parcel.readValue(String::class.java.classLoader) as String
        this.timestamp = parcel.readValue(String::class.java.classLoader) as String
        this.v = parcel.readValue(Int::class.java.classLoader) as Int
    }

    constructor() {}

    override fun toString(): String {
        return "question -> $question correctAnswer -> $correctAnswer timestamp -> $timestamp"
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(tags)
        dest.writeList(incorrectAnswers)
        dest.writeValue(id)
        dest.writeValue(createdBy)
        dest.writeValue(type)
        dest.writeValue(difficulty)
        dest.writeValue(question)
        dest.writeValue(correctAnswer)
        dest.writeValue(timestamp)
        dest.writeValue(v)
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
