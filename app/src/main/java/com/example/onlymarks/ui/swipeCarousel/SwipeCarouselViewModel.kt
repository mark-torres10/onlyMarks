package com.example.onlymarks.ui.swipeCarousel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SwipeCarouselViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Swipe Carousel Fragment"
    }
    val text: LiveData<String> = _text

}