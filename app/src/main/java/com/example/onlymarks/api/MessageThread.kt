package com.example.onlymarks.api

import java.sql.Timestamp

data class MessageThread (
    var threadId: Int,
    var threadOwnerId: Int, // default assigned to whoever wrote first message
    var threadReceiverId: Int, // default assigned to whoever received the first message
    var createdAtTimestamp: Timestamp,
    var lastMessageTimestamp: Timestamp,
    var lastMessageReadBool: Boolean,
    var messages: MutableList<Message>
)