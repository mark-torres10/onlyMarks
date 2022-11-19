package com.example.onlymarks.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlymarks.dataclasses.MessageThread
import com.example.onlymarks.seedData.getSeedMessageThreads

class MessageThreadsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    private val _defaultMessageThreads = MutableLiveData<List<MessageThread>>().apply {
        value = getSeedMessageThreads(20)
    }
    val text: LiveData<String> = _text
    private val messageThreadsList: MutableLiveData<List<MessageThread>> = _defaultMessageThreads

    fun setMessageThreadsList(newMessageThreadsList: List<MessageThread>) {
        messageThreadsList.value = newMessageThreadsList
    }

    fun setUpdatedMessageThreadInThreadsList(updatedMessageThread: MessageThread) {
        // update one of the message threads currently in the list.
        val currentThreads = messageThreadsList.value!!
        var updatedMessageThreadsList = mutableListOf<MessageThread>()
        for (messageThread in currentThreads) {
            if (messageThread.threadId == updatedMessageThread.threadId) {
                updatedMessageThreadsList.add(updatedMessageThread)
            } else {
                updatedMessageThreadsList.add(messageThread)
            }
        }
        messageThreadsList.value = updatedMessageThreadsList
    }

    fun observeMessageThreadsList(): MutableLiveData<List<MessageThread>> {
        return messageThreadsList
    }
}