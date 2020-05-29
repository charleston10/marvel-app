package com.charleston.marvelapp.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.R
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main),
    ThemeAdapter.Listener {

    private val viewModel by viewModel<MainViewModel>()

    private val themeAdapter = ThemeAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupThemes()
        observerViewModel()
    }

    override fun onClickListener(themeModel: ThemeModel) {
        viewModel.selectTheme(themeModel)
    }

    private fun observerViewModel() {
        viewModel.run {
            listThemeLiveData.observe(viewLifecycleOwner,
                Observer {
                    themeAdapter.loadItems(it)
                })

            stateSingleEvent.observe(viewLifecycleOwner,
                Observer {
                    handlerState(it)
                })
        }
    }

    private fun setupThemes() {
        list_theme.run {
            layoutManager =
                LinearLayoutManager(this@MainFragment.context, RecyclerView.HORIZONTAL, false)
            adapter = themeAdapter
        }

        LinearSnapHelper().attachToRecyclerView(list_theme)
    }

    private fun handlerState(state: MainState) {
        when (state) {
            is MainState.StartScreenList -> startList(state.themeSelected)
        }
    }

    private fun startList(themeModel: ThemeModel) {
        view?.post {
            val action = MainFragmentDirections.actionMainFragmentToListFragment(themeModel)
            findNavController().navigate(action)
        }
    }
}