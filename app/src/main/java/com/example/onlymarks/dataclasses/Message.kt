package com.example.onlymarks.dataclasses

import android.os.Parcelable
import java.sql.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message (
    var messageId: Int,
    var threadId: Int,
    var sentFromId: Int,
    var sentToId: Int,
    var createdAtTimestamp: Timestamp,
    var message: String,
    var attachments: MutableList<String> // string with paths to attachments
) : Parcelable