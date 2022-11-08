package com.example.onlymarks.api

data class Notification (
    var id: Int,
    var notificationType: String,
    var sourceUserId: Int,
    var sourceUserName: String,
    var hasBeenViewed: Boolean = false
)