package com.charleston.marvelapp.screens.list

sealed class ListState {
    object Loading: ListState()
    object Error: ListState()
}