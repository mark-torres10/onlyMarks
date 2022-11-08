package com.example.onlymarks.seedData

import com.example.onlymarks.api.Notification
import java.sql.Timestamp

fun getSeedNotifications(numNotifications: Int): List<Notification> {
    // TODO: turn defaultNotificationType into enum
    val defaultNotificationType = "swipedOnYourProfile"
    val defaultSourceUserId = 1
    val defaultSourceUserName = "Vladimir Putin"

    var listOfNotifications = mutableListOf<Notification>()

    for(i in 0..numNotifications) {
        listOfNotifications.add(
            i, Notification(
                i, defaultNotificationType, defaultSourceUserId, defaultSourceUserName
            )
        )
    }

    return listOfNotifications.toList()
}
