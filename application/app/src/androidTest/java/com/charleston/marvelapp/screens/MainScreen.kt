package com.charleston.marvelapp.screens

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.charleston.marvelapp.R

class MainScreen : Screen<MainScreen>() {
    val txtError = KTextView { withText("unfortunately we were unable to get the data")}
}