package com.example.onlymarks.seedData

import com.example.onlymarks.dataclasses.Message
import net.datafaker.Faker
import java.util.concurrent.TimeUnit

val messageFaker = Faker()

fun getSingleSeedMessage(
    sourceThreadId: Int,
    sentFromId: Int,
    sentToId: Int,
    customMessage: String? = null
): Message {
    var messageId = messageFaker.idNumber().hashCode()
    var createdAtTimestamp = messageFaker.date().past(48, TimeUnit.HOURS)
    var message: String
    if (customMessage == null) {
        message = messageFaker.greekPhilosopher().quote()
    } else {
        message = customMessage
    }
    var attachments = mutableListOf<String>()

    return Message(
        messageId, sourceThreadId, sentFromId, sentToId, createdAtTimestamp, message, attachments
    )
}