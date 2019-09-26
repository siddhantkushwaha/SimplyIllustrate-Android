package com.simplyillustrate.simplyillustrate.entity

import com.google.gson.annotations.SerializedName
import java.util.*

class Tag {

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
}