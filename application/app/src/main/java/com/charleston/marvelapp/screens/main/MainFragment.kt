package com.charleston.marvelapp.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.databinding.FragmentMainBinding
import com.charleston.marvelapp.extensions.divisorLastList
import com.charleston.marvelapp.screens.adapters.ListAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(),
    ThemeAdapter.Listener,
    ListAdapter.Listener {

    private val viewModel by viewModel<MainViewModel>()

    private val themeAdapter = ThemeAdapter(this)
    private val eventAdapter = ListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = this@MainFragment
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupThemes()
        setupEvents()
        observerViewModel()
    }

    override fun onClickListener(themeModel: ThemeModel) {
        viewModel.selectTheme(themeModel)
    }

    override fun onClickListener(model: ItemModel) {
        startDetail(model)
    }

    private fun observerViewModel() {
        viewModel.run {
            listThemeLiveData.observe(viewLifecycleOwner,
                Observer {
                    themeAdapter.loadItems(it)
                })

            listEventLiveData.observe(viewLifecycleOwner,
                Observer {
                    eventAdapter.loadItems(it)
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

    private fun setupEvents() {
        list_events.run {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
        }
        list_events.addItemDecoration(list_events.context.divisorLastList())
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

    private fun startDetail(model: ItemModel) {
        view?.post {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(model)
            findNavController().navigate(action)
        }
    }
}