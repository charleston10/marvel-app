package com.charleston.marvelapp.screens.list

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListCharactersUseCase
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeEnum
import com.charleston.domain.model.ThemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class ListViewModel(
    private val listCharactersUseCase: ListCharactersUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val listItem: ArrayList<ItemModel> = arrayListOf()
    private lateinit var themeSelected: ThemeModel

    private var initialized = false
    private var page = 1
    private var perPage = 10
    private var breakPagination = false

    private val listMutableLiveData = MutableLiveData<List<ItemModel>>()
    val listLiveData = listMutableLiveData

    val totalShowing = ObservableInt()
    val state = ObservableField<ListState>()

    fun loadList(themeSelected: ThemeModel) {
        this.themeSelected = themeSelected

        if (!initialized) {
            loadListByTheme()
            initialized = true
        }
    }

    fun nextPage() {
        if (!breakPagination && state.get() !is ListState.LoadingPage) {
            page++
            loadListByTheme()
        }
    }

    private fun loadListByTheme() {
        when (themeSelected.type) {
            ThemeEnum.CHARACTERS -> {
                listCharacters()
            }
            ThemeEnum.SERIES -> {
                listCharacters()
            }
            ThemeEnum.COMICS -> {
                listCharacters()
            }
        }
    }

    private fun listCharacters() {
        if (listItem.isEmpty()) state.set(ListState.Loading)
        else state.set(ListState.LoadingPage)

        try {
            coroutineScope.launch {
                val list = listCharactersUseCase.execute(page, perPage)
                handlerSuccess(list)
            }
        } catch (e: Exception) {
            state.set(ListState.Error)
        }
    }

    private fun handlerSuccess(list: List<ItemModel>) {
        if (list.isNotEmpty()) {
            listItem.addAll(list)
            listMutableLiveData.postValue(list)
            state.set(ListState.Success)
            treatPage(list.size)
        } else {
            state.set(ListState.Empty)
        }

        totalShowing.set(listItem.size)
    }

    /**
     * check result to know if you need to remove
     * the pagination when you reach the end
     */
    private fun treatPage(total: Int) {
        if (total < perPage) {
            breakPagination = true
        }
    }
}