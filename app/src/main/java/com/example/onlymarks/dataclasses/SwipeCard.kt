package com.example.onlymarks.dataclasses

data class SwipeCard(
    var id: Int,
    var name: String,
    var bio: String,
    var profilePics: List<String>,
    var age: Int,
    var distance: Int,
    var theyLikeYouBool: Boolean = false,
    var youLikeThemBool: Boolean = false
)