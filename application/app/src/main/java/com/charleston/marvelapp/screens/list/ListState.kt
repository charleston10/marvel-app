package com.charleston.marvelapp.screens.list

sealed class ListState {
    object Loading: ListState()
    object Error: ListState()
    object Success: ListState()
    object Empty: ListState()

    object LoadingPage: ListState()
    object LoadingSearch: ListState()
}