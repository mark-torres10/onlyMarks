package com.example.onlymarks.seedData

import com.example.onlymarks.api.SwipeCard

fun getSeedSwipeCards(numCards: Int): List<SwipeCard> {
    val defaultBio = "da. da. da. Take shots with me."

    var listOfCards = mutableListOf<SwipeCard>()

    val maxListOfMarks = LIST_OF_MARKS.size
    for(i in 0..numCards) {
        val idx = i % maxListOfMarks
        val name = LIST_OF_MARKS[idx]
        val profilePicLink = MARK_TO_IMAGE[name]!!
        val age = MARK_TO_AGE[name]!!
        val distance = (10 * Math.random()).toInt()
        listOfCards.add(
            i, SwipeCard(
                i, name, defaultBio, profilePicLink, age, distance
            )
        )
    }

    return listOfCards.toList()
}
