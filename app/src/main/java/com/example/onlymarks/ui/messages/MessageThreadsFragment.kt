package com.example.onlymarks.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlymarks.databinding.FragmentMessagesBinding

class MessageThreadsFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: MessageThreadsViewModel
    private lateinit var adapter: MessagesAdapter
    private lateinit var manager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(MessageThreadsViewModel::class.java)

        _binding = FragmentMessagesBinding.inflate(inflater, container, false)

        manager = LinearLayoutManager(binding.root.context)

        val currentMessageThreads = viewModel.observeMessageThreadsList().value!!
        adapter = MessagesAdapter()
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