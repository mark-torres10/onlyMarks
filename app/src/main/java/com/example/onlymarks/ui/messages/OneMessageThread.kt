package com.example.onlymarks.ui.messages

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlymarks.databinding.ActivityOneMessageThreadBinding
import com.example.onlymarks.dataclasses.Message
import com.example.onlymarks.dataclasses.MessageThread

class OneMessageThread: AppCompatActivity() {
    private lateinit var binding: ActivityOneMessageThreadBinding
    private var messagePersonName: String? = null
    private var messageThread: MessageThread? = null
    //private var messageList: List<Message>? = null
    private lateinit var adapter: MessageAdapter
    private lateinit var manager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneMessageThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityThatCalled = intent

        val callingBundle = activityThatCalled.extras
        messagePersonName = callingBundle?.getString("messagePersonName")
        messageThread = callingBundle?.getParcelable("messageThread")

        messagePersonName?.apply {
            binding.messageHeaderText.text = this
        }

        adapter = MessageAdapter()
        messageThread?.apply {
            adapter.updateMessageThread(this)
        }
        manager = LinearLayoutManager(binding.root.context)

        binding.threadMessagesRecyclerView.adapter = adapter
        binding.threadMessagesRecyclerView.layoutManager = manager
    }
}