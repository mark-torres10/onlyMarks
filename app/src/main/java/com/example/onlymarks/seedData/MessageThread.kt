package com.example.onlymarks.seedData

import java.util.UUID
import com.example.onlymarks.dataclasses.Message
import com.example.onlymarks.dataclasses.MessageThread
import net.datafaker.Faker

val messageThreadFaker = Faker()

const val DEFAULT_NUM_MESSAGES_PER_THREAD = 5
const val DEFAULT_SOURCE_USER_ID = 1
const val DEFAULT_RECIPIENT_USER_ID = 2

fun getSeedMessageThread(
    numMessages: Int = DEFAULT_NUM_MESSAGES_PER_THREAD,
    sourceUserId: Int = DEFAULT_SOURCE_USER_ID,
    recipientUserId: Int = DEFAULT_RECIPIENT_USER_ID
): MessageThread {
    var messagesList = mutableListOf<Message>()
    var threadId = UUID.randomUUID().hashCode()
    var otherUserName = messageThreadFaker.name().fullName().toString()

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

    // sort messages list by createdAtTimestamp (ascending)
    messagesList.sortBy{it.createdAtTimestamp}

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
        threadId, sourceUserId, recipientUserId, otherUserName, earliestMessageTimestamp,
        latestMessageTimestamp, false, messagesList
    )
}

fun getSeedMessageThreads(
    numThreads: Int,
    numMessagesPerThread: Int = DEFAULT_NUM_MESSAGES_PER_THREAD,
    sourceUserId: Int = DEFAULT_SOURCE_USER_ID,
    recipientUserId: Int = DEFAULT_RECIPIENT_USER_ID
): List<MessageThread> {
    var listOfMessageThreads = mutableListOf<MessageThread>()
    for (i in 0..numThreads) {
        val messageThread = getSeedMessageThread(
            numMessagesPerThread, sourceUserId, recipientUserId
        )
        listOfMessageThreads.add(i, messageThread)
    }
    return listOfMessageThreads.toList()
}