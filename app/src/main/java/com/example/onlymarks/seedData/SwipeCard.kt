package com.example.onlymarks.seedData

import com.example.onlymarks.api.SwipeCard

fun getSeedSwipeCards(numCards: Int): List<SwipeCard> {
    // get a default set of cards
    val defaultName = "Vladimir Putin"
    val defaultBio = "da. da. da. Take shots with me."
    val defaultProfilePicSrc = "@drawable/putin_horse"
    val defaultAge = 28
    val defaultDistance = 5

    var listOfCards = mutableListOf<SwipeCard>()

    for(i in 0..numCards) {
        listOfCards.add(
            i, SwipeCard(
                i, defaultName, defaultBio, defaultProfilePicSrc,
                defaultAge, defaultDistance
            )
        )
    }

    return listOfCards.toList()
}
