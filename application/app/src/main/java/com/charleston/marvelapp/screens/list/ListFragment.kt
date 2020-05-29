package com.charleston.marvelapp.screens.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.charleston.domain.model.CharacterModel
import com.charleston.marvelapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by viewModel<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        initialize()
    }

    private fun initialize() {
        arguments?.let {
            val safeArgs = ListFragmentArgs.fromBundle(it)
            val model = safeArgs.themeSelected
            viewModel.loadList(model)
        }
    }

    private fun observerViewModel() {
        viewModel.run {
            listLiveData.observe(viewLifecycleOwner,
                Observer {
                    loadList(it)
                })
        }
    }

    private fun loadList(list: List<CharacterModel>) {
        Toast.makeText(context, "Total de items ${list.size}", Toast.LENGTH_SHORT).show()
    }
}