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
        value = getSeedMessageThreads()
    }
    val text: LiveData<String> = _text
    private val messageThreadsList: MutableLiveData<List<MessageThread>> = _defaultMessageThreads

    fun setMessageThreadsList(newMessageThreadsList: List<MessageThread>) {
        messageThreadsList.value = newMessageThreadsList
    }

    fun observeMessageThreadsList(): MutableLiveData<List<MessageThread>> {
        return messageThreadsList
    }
}