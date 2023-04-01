package com.example.bookfinder.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookfinder.data.model.remote.Book
import com.example.bookfinder.data.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun searchBooks() {
        viewModelScope.launch {
            _books.value = repository.searchBooks(_query.value)
        }
    }

    init {
        searchBooks()
    }
    fun setQuery(query: String) {
        _query.value = query
        searchBooks()
    }
}