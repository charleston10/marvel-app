package com.charleston.marvelapp.screens.main

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.appcompat.widget.SwitchCompat
import com.charleston.marvelapp.R
import com.charleston.marvelapp.extensions.toggle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindToolbar()
        bindNavigationDrawer()
    }

    private fun bindToolbar() {
        toolbar_main.iv_menu.setOnClickListener {
            drawer_layout.toggle()
        }
    }

    private fun bindNavigationDrawer() {
        val switch = navigation_view.menu.getItem(0).actionView as SwitchCompat
        switch.isChecked =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES

        switch.setOnCheckedChangeListener { _, isChecked ->
            changeThemeDayNight(isChecked)
            return@setOnCheckedChangeListener
        }
    }

    private fun changeThemeDayNight(isDark: Boolean) {
        if (isDark) {
            setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            setDefaultNightMode(MODE_NIGHT_NO)
        }
    }
}