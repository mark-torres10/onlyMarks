package com.example.onlymarks.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlymarks.api.Notification
import com.example.onlymarks.seedData.getSeedNotifications

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    private val _defaultNotifications = MutableLiveData<List<Notification>>().apply {
        value = getSeedNotifications(5)
    }
    val text: LiveData<String> = _text
    private val allNotifications: MutableLiveData<List<Notification>> = _defaultNotifications


    fun setNotifications(newNotificationsList: List<Notification>) {
        allNotifications.value = newNotificationsList
    }

    fun observeNotifications(): MutableLiveData<List<Notification>> {
        return allNotifications
    }
}