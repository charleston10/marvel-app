package com.charleston.marvelapp.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.R
import com.charleston.marvelapp.screens.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main), ThemeAdapter.Listener {

    private val themeAdapter = ThemeAdapter(this)

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupThemes()
        observerViewModel()
    }

    override fun onClickListener(themeModel: ThemeModel) {
        Toast.makeText(this, themeModel.name, Toast.LENGTH_SHORT).show()
    }

    private fun observerViewModel() {
        viewModel.run {
            listThemeLiveData.observe(this@MainActivity,
                Observer {
                    themeAdapter.loadItems(it)
                })
        }
    }

    private fun setupThemes() {
        list_theme.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = themeAdapter
        }

        LinearSnapHelper().attachToRecyclerView(list_theme)
    }
}
