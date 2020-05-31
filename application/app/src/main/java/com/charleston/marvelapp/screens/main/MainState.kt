package com.charleston.marvelapp.screens.main

import com.charleston.domain.model.ThemeModel

sealed class MainState {
    class StartScreenList(val themeSelected: ThemeModel) : MainState()
    object LoadingEvent : MainState()
    object SuccessListEvent : MainState()
}