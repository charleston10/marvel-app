package com.charleston.marvelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.charleston.marvelapp.extensions.divisorLastList
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var featuredAdapter: CharacterFeaturedAdapter

    private val viewModel by viewModel<MarvelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListCharacters()
        setupListSeries()
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.listFeaturedLiveData.observe(this, Observer {
            featuredAdapter.loadItems(it)
        })
    }

    private fun setupListCharacters() {
        featuredAdapter = CharacterFeaturedAdapter()

        list_characters.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = featuredAdapter
        }

        LinearSnapHelper().attachToRecyclerView(list_characters)
    }

    private fun setupListSeries(){
        list_series.run {
            layoutManager = LinearLayoutManager(context)
            adapter = featuredAdapter
            addItemDecoration(divisorLastList())
        }
    }
}
