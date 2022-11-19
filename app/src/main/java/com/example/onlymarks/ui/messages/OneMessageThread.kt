package com.example.onlymarks.ui.messages

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlymarks.R
import com.example.onlymarks.databinding.ActivityOneMessageThreadBinding
import com.example.onlymarks.dataclasses.Message
import com.example.onlymarks.dataclasses.MessageThread
import java.sql.Timestamp

class OneMessageThread: AppCompatActivity() {
    private lateinit var binding: ActivityOneMessageThreadBinding
    private var messagePersonName: String? = null
    private var messageThread: MessageThread? = null
    //private var messageList: List<Message>? = null
    private lateinit var adapter: MessageAdapter
    private lateinit var manager: LinearLayoutManager

    //private val viewModel: MessageThreadsViewModel by activityViewModel

    private fun createNewMessage(messageText: String): Message {
        val messageId = 1 // TODO: get uuid via other method
        val threadId = messageThread?.threadId!!
        val sentFromId = messageThread?.threadOwnerId!!
        val sentToId = messageThread?.threadReceiverId!!
        val createdAtTimestamp = Timestamp(10000) // TODO: get real timestamp
        val attachments = mutableListOf<String>()

        return Message(
            messageId, threadId, sentFromId, sentToId, createdAtTimestamp, messageText, attachments
        )
    }

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
        // https://stackoverflow.com/questions/29474876/make-recycler-view-show-rows-from-bottom
        //manager.stackFromEnd = true
        manager.reverseLayout = false

        binding.threadMessagesRecyclerView.adapter = adapter
        binding.threadMessagesRecyclerView.layoutManager = manager

        if (messageThread != null) {
            binding.threadMessagesRecyclerView.scrollToPosition(messageThread!!.messages.size - 1)
        }

        binding.newTextMessage.doAfterTextChanged {
            if (it?.length!! > 0) {
                binding.newMessageSubmitBtn.alpha = 1.0F
            }
        }

        binding.newMessageSubmitBtn.setOnClickListener {
            val textMessage = binding.newTextMessage.text.toString()
            if (textMessage.isNotEmpty()) {
                // update adapter with new message
                val newMessage = createNewMessage(textMessage)
                messageThread?.messages!!.add(newMessage)
                Log.d("XXX", "Number of messages after submit: ${messageThread!!.messages.size}")
                adapter.updateMessageThread(messageThread!!)
                // clear edittext
                binding.newTextMessage.text.clear()
                // update RecyclerView scrolling
                binding.threadMessagesRecyclerView.scrollToPosition(messageThread!!.messages.size - 1)
            }
        }
        if (supportActionBar != null) {
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("XXX", "Clicking back button")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}