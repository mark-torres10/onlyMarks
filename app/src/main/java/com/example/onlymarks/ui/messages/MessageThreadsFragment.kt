package com.example.onlymarks.ui.messages

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlymarks.databinding.FragmentMessagesBinding
import com.example.onlymarks.dataclasses.MessageThread

class MessageThreadsFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: MessageThreadsViewModel
    private lateinit var adapter: MessageThreadsAdapter
    private lateinit var manager: LinearLayoutManager
    private lateinit var currentMessageThreads: List<MessageThread>

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Intent? = it.data
            var messageThread: MessageThread?

            data?.extras?.apply {
                messageThread = getParcelable("updatedMessageThread")
                updateData(messageThread!!)
                adapter.updateMessageThreadsList(currentMessageThreads)
                binding.messagesRecyclerView.adapter = adapter
            }
        } else {
            Log.d("XXX", "Bad activity code returned: ${it.resultCode}")
        }
    }

    private fun hasMessageThreadChanged(
        oldMessageThread: MessageThread,
        newMessageThread: MessageThread
    ): Boolean {
        // check two message threads to see if there's been any change.
        if (oldMessageThread.threadId != newMessageThread.threadId) {
            Log.w("XXX", "Comparing two threads that are different.")
            return false
        }
        return newMessageThread.messages.size > oldMessageThread.messages.size
    }

    // update message thread list with either a new thread or an update to an old thread.
    private fun updateData(newMessageThread: MessageThread) {
        var newMessageThreadList = mutableListOf<MessageThread>()

        // check if message is already in thread list.
        var hasMessageThreadChangedBool = false
        for (thread in currentMessageThreads) {
            if (thread.threadId == newMessageThread.threadId) {
                hasMessageThreadChangedBool = hasMessageThreadChanged(
                    thread, newMessageThread
                )
            }
        }

        // if message thread has changed, put new message at top
        if(hasMessageThreadChangedBool) {
            // add newest message first, so it appears at the top of the RecyclerView
            newMessageThreadList.add(newMessageThread)
            for (thread in currentMessageThreads) {
                if (thread.threadId != newMessageThread.threadId) {
                    newMessageThreadList.add(thread)
                }
            }
        } else {
            // return in regular order
            newMessageThreadList = currentMessageThreads.toMutableList()
        }

        viewModel.setMessageThreadsList(newMessageThreadList)
        currentMessageThreads = newMessageThreadList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(MessageThreadsViewModel::class.java)

        _binding = FragmentMessagesBinding.inflate(inflater, container, false)

        manager = LinearLayoutManager(binding.root.context)

        currentMessageThreads = viewModel.observeMessageThreadsList().value!!

        adapter = MessageThreadsAdapter(resultLauncher)
        adapter.updateMessageThreadsList(currentMessageThreads)

        binding.messagesRecyclerView.adapter = adapter
        binding.messagesRecyclerView.layoutManager = manager

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}