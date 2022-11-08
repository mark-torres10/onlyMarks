package com.example.onlymarks.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlymarks.api.Notification

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    private val _defaultNotifications = MutableLiveData<List<Notification>>().apply {
        value = getDefaultNotifications(5)
    }
    val text: LiveData<String> = _text
    private val allNotifications: MutableLiveData<List<Notification>> = _defaultNotifications

    fun getDefaultNotifications(numNotifications: Int): List<Notification> {
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

    fun setNotifications(newNotificationsList: List<Notification>) {
        allNotifications.value = newNotificationsList
    }

    fun observeNotifications(): MutableLiveData<List<Notification>> {
        return allNotifications
    }
}