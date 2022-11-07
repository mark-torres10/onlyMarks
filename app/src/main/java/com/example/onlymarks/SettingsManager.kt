package com.example.onlymarks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.onlymarks.databinding.SettingsManagerBinding

class SettingsManager : AppCompatActivity() {

    private lateinit var settingsManagerBinding: SettingsManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXX", "Launching settings manager")
    }
}