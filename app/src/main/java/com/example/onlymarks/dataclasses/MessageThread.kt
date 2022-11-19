package com.example.onlymarks.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class MessageThread (
    var threadId: Int,
    var threadOwnerId: Int, // TODO: assign to the current user by default
    var threadReceiverId: Int, // TODO: assign to recipient
    var otherUserName: String, //  TODO: should be obtained from a join on a User table. Name of other user.
    var createdAtTimestamp: Timestamp,
    var lastMessageTimestamp: Timestamp,
    var lastMessageReadBool: Boolean,
    var messages: MutableList<Message>
) : Parcelable