package com.example.bookfinder.model

data class Book(
    val id: String,
    val kind: String,
    val searchInfo: SearchInfo,
    val selfLink: String?, //kitabın id'si ile arama yapma linki
    val volumeInfo: VolumeInfo?,
    var isFavorite: Boolean = false
)