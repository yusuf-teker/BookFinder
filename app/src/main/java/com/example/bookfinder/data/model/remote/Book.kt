package com.example.bookfinder.data.model.remote

import com.example.bookfinder.data.model.room.FavoriteBook

data class Book(
    val id: String,
    val kind: String,
    val searchInfo: SearchInfo,
    val selfLink: String?, //kitabın id'si ile arama yapma linki
    val volumeInfo: VolumeInfo?,
    var isFavorite: Boolean = false
)

fun Book.toFavoriteBook(): FavoriteBook{
    val favoriteBook = FavoriteBook(
         id = id,
     kind = kind,
     authors = volumeInfo?.authors ?: listOf(),
     categories  = volumeInfo?.categories ?: listOf(),
     description = volumeInfo?.description ?: "",
     thumbnail = volumeInfo?.imageLinks?.thumbnail ?: "",
     language = volumeInfo?.language ?: "",
     pageCount = volumeInfo?.pageCount ?: 0,
     publishedDate = volumeInfo?.publishedDate ?: "",
     publisher = volumeInfo?.publisher ?: "",
     title  = volumeInfo?.title ?: "",
     isFavorite = true,
     myNotes = "",
     readingStatus = -1
    )
    return favoriteBook
}