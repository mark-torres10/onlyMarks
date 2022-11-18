package com.example.onlymarks.dataclasses

data class Notification (
    var id: Int,
    var notificationType: String,
    var sourceUserId: Int,
    var sourceUserName: String,
    var hasBeenViewed: Boolean = false
)