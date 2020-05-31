package com.charleston.marvelapp.screens.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ItemModel
import com.charleston.marvelapp.databinding.FragmentListBinding
import com.charleston.marvelapp.extensions.animateFallDown
import com.charleston.marvelapp.extensions.divisorLastList
import kotlinx.android.synthetic.main.container_list_result.*
import kotlinx.android.synthetic.main.container_list_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(), ListAdapter.Listener {

    private val viewModel by viewModel<ListViewModel>()

    private val listAdapter = ListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = this@ListFragment
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        initialize()
        setupList()
        bindSearch()
    }

    override fun onClickListener(model: ItemModel) {

    }

    private fun initialize() {
        arguments?.let {
            val safeArgs = ListFragmentArgs.fromBundle(it)
            viewModel.loadList(safeArgs.themeSelected)
        }
    }

    private fun observerViewModel() {
        viewModel.run {
            listLiveData.observe(viewLifecycleOwner,
                Observer {
                    loadList(it)
                })

            clearListSingleLiveEvent.observe(viewLifecycleOwner,
                Observer {
                    if (it) {
                        listAdapter.clear()
                    }
                })
        }
    }

    private fun setupList() {
        val linearLayoutManager = LinearLayoutManager(context)

        list.run {
            layoutManager = linearLayoutManager
            adapter = listAdapter
        }

        list.addItemDecoration(list.context.divisorLastList())
        list.addOnScrollListener(setupPagination(linearLayoutManager))
    }

    private fun loadList(items: List<ItemModel>) {
        if (listAdapter.itemCount == 0) {
            list.animateFallDown()
        }
        listAdapter.loadItems(items)
    }

    private fun setupPagination(layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visiblePosition =
                    layoutManager.findLastCompletelyVisibleItemPosition()

                if (visiblePosition == listAdapter.itemCount - 10) {
                    viewModel.nextPage()
                }
            }
        }
    }

    private fun bindSearch() {
        val textWatcher = object : TextWatcher {
            private var searchFor = ""

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                if (searchText.length == 1) {//conditional for know first search (clear list)
                    viewModel.searchReset()
                } else if (searchText.isBlank()) {//conditional for edit text was cleared
                    viewModel.clearSearch()
                    return
                }

                searchFor = searchText
                viewModel.search(searchText, searchFor)
            }

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
        }

        txt_search.addTextChangedListener(textWatcher)
    }
}