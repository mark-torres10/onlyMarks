package com.example.onlymarks.ui.messages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlymarks.databinding.ActivityOneMessageThreadBinding

class OneMessageThread: AppCompatActivity() {
    private lateinit var binding: ActivityOneMessageThreadBinding
    private var messagePersonName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneMessageThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityThatCalled = intent

        val callingBundle = activityThatCalled.extras
        messagePersonName = callingBundle?.getString("messagePersonName")

        messagePersonName?.apply {
            binding.messageHeaderText.text = this
        }


    }
}