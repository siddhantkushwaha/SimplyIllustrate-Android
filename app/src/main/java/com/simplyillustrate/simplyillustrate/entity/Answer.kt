package com.simplyillustrate.simplyillustrate.entity

import com.google.gson.annotations.SerializedName
import java.util.*

class Answer() {

    @SerializedName("_id")
    var id: String? = null

    @SerializedName("created_by")
    var createdBy: String? = null

    @SerializedName("question_id")
    var question_id: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("timestamp")
    var timestamp: Date? = null

    @SerializedName("is_accepted")
    var isAccepted: Boolean? = null
}