package com.example.onlymarks.seedData

import com.example.onlymarks.api.Message
import com.example.onlymarks.api.MessageThread
import net.datafaker.Faker

val messageThreadFaker = Faker()

fun getSeedMessageThread(
    numMessages: Int,
    sourceUserId: Int,
    recipientUserId: Int
): MessageThread {
    var messagesList = mutableListOf<Message>()
    var threadId = messageThreadFaker.idNumber().hashCode()

    for (i in 0..numMessages) {
        // have a message thread with 1:1 back and forth messages
        var sentFromId: Int
        var sentToId: Int
        if (i % 2 == 0) {
            sentFromId = sourceUserId
            sentToId = recipientUserId
        } else {
            sentFromId = recipientUserId
            sentToId = sourceUserId
        }
        messagesList.add(i, getSingleSeedMessage(threadId, sentFromId, sentToId))
    }

    // get timestamp of latest and earliest dates in listOfNotifications
    var earliestMessageTimestamp = messagesList[0].createdAtTimestamp
    var latestMessageTimestamp = messagesList[0].createdAtTimestamp

    for (message in messagesList) {
        val messageTimestamp = message.createdAtTimestamp
        if (messageTimestamp.before(earliestMessageTimestamp)) {
            earliestMessageTimestamp = messageTimestamp
        } else if (messageTimestamp.after(latestMessageTimestamp)) {
            latestMessageTimestamp = messageTimestamp
        }
    }
    return MessageThread(
        threadId, sourceUserId, recipientUserId, earliestMessageTimestamp,
        latestMessageTimestamp, false, messagesList
    )
}