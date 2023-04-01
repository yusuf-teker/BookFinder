package com.example.bookfinder.screens.common


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookfinder.data.model.remote.Book


@Composable
fun BookList(items: List<Book>) {

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(items.size) {
                BookItem(book = items[it])
            }

        }
    }
}