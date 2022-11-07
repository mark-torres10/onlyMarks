package com.example.onlymarks.api

data class SwipeCard (
    var id: Int,
    var name: String,
    var bio: String,
    var profile_pic_src: String,
    var age: Int,
    var distance: Int,
    var theyLikeYouBool: Boolean = false,
    var youLikeThemBool: Boolean = false
)