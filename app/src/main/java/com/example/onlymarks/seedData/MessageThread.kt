package com.example.onlymarks.seedData

import java.util.UUID
import com.example.onlymarks.dataclasses.Message
import com.example.onlymarks.dataclasses.MessageThread

const val DEFAULT_NUM_MESSAGES_PER_THREAD = 5
const val DEFAULT_SOURCE_USER_ID = 1
const val DEFAULT_RECIPIENT_USER_ID = 2

fun getSeedMessageThread(
    numMessages: Int = DEFAULT_NUM_MESSAGES_PER_THREAD,
    sourceUserId: Int = DEFAULT_SOURCE_USER_ID,
    recipientUserId: Int = DEFAULT_RECIPIENT_USER_ID,
    recipientUserName: String,
    userSentFirstMessageBool: Boolean
): MessageThread {
    var messagesList = mutableListOf<Message>()
    var threadId = UUID.randomUUID().hashCode()
    var otherUserName = recipientUserName

    val dummyMessages = DUMMY_MESSAGES_LIST

    for (i in 0 until numMessages) {
        // have a message thread with 1:1 back and forth messages
        var sentFromId: Int
        var sentToId: Int
        if (userSentFirstMessageBool) {
            if (i % 2 == 0) {
                sentFromId = sourceUserId
                sentToId = recipientUserId
            } else {
                sentFromId = recipientUserId
                sentToId = sourceUserId
            }
        } else {
            if (i % 2 == 0) {
                sentFromId = recipientUserId
                sentToId = sourceUserId
            } else {
                sentFromId = sourceUserId
                sentToId = recipientUserId
            }
        }
        val customMessage = dummyMessages[i]
        val newMessageObj = getSingleSeedMessage(threadId, sentFromId, sentToId, customMessage)
        messagesList.add(i, newMessageObj)
    }

    // sort messages list by createdAtTimestamp (ascending)
    // messagesList.sortBy{it.createdAtTimestamp}

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
    numMessagesPerThread: Int = DEFAULT_NUM_MESSAGES_PER_THREAD,
    sourceUserId: Int = DEFAULT_SOURCE_USER_ID,
    recipientUserId: Int = DEFAULT_RECIPIENT_USER_ID
): List<MessageThread> {
    var listOfMessageThreads = mutableListOf<MessageThread>()

    for ((idx, name) in LIST_OF_MARKS.withIndex()) {
        var userSentFirstMessageBool = idx % 2 == 0
        val messageThread = getSeedMessageThread(
            numMessagesPerThread, sourceUserId, recipientUserId, name, userSentFirstMessageBool
        )
        listOfMessageThreads.add(idx, messageThread)
    }

    return listOfMessageThreads.toList()
}