package com.example.onlymarks.ui.swipeCarousel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlymarks.api.SwipeCard
import com.example.onlymarks.seedData.getSeedSwipeCards

class SwipeCarouselViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Swipe Carousel Fragment"
    }
    val text: LiveData<String> = _text
    private val _defaultCards = MutableLiveData<List<SwipeCard>>().apply {
        value = getSeedSwipeCards(5)
    }
    private val swipeCards: MutableLiveData<List<SwipeCard>> = _defaultCards

    fun addSwipeCard() {
        // TODO: check that card isn't already in stack. If not,
        // TODO: then add to list of cards.
        // https://github.com/mcso-ap/hw4v2-mark-torres10/blob/main/app/src/main/java/edu/cs371m/reddit/ui/MainViewModel.kt#L89
    }

    fun setSwipeCards(newSwipeCards: List<SwipeCard>) {
        swipeCards.value = newSwipeCards
    }

    fun observeSwipeCards(): LiveData<List<SwipeCard>> {
        return swipeCards
    }


}