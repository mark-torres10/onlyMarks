package com.example.onlymarks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.onlymarks.databinding.SwipeCarouselBinding

class SwipeCarousel : AppCompatActivity() {

    private lateinit var swipeCarouselBinding: SwipeCarouselBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XXX", "Launching Swipe Carousel")
    }
}