package com.example.onlymarks.seedData

import com.example.onlymarks.dataclasses.SwipeCard

fun getSeedSwipeCards(numCards: Int): List<SwipeCard> {
    var listOfCards = mutableListOf<SwipeCard>()

    val maxListOfMarks = LIST_OF_MARKS.size
    for(i in 0..numCards) {
        val idx = i % maxListOfMarks
        val name = LIST_OF_MARKS[idx]
        val profilePicLink = MARK_TO_IMAGE[name]!!
        val age = MARK_TO_AGE[name]!!
        val bio = MARK_TO_BIO[name]!!
        val distance = (10 * Math.random()).toInt()
        listOfCards.add(
            i, SwipeCard(i, name, bio, profilePicLink, age, distance)
        )
    }

    return listOfCards.toList()
}
