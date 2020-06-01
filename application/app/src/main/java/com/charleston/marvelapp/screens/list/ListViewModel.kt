package com.charleston.marvelapp.screens.list

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListItemUseCase
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.core.SingleLiveEvent
import kotlinx.coroutines.*
import java.lang.Exception

class ListViewModel(
    private val listUseCase: ListItemUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val listItem: ArrayList<ItemModel> = arrayListOf()

    lateinit var themeSelected: ThemeModel

    private var initialized = false

    private var page = 1
    private var perPage = 30
    private var isPagination = false
    private var breakPagination = false

    private var nameSearch = ""

    private val listMutableLiveData = MutableLiveData<List<ItemModel>>()
    val listLiveData = listMutableLiveData

    private val clearListMutableSingleLiveEvent = SingleLiveEvent<Boolean>()
    val clearListSingleLiveEvent = clearListMutableSingleLiveEvent

    val totalShowing = ObservableInt()
    val state = ObservableField<ListState>()

    fun loadList(themeSelected: ThemeModel) {
        this.themeSelected = themeSelected

        if (!initialized) {
            loadListItem()
            initialized = true
        }
    }

    fun nextPage() {
        if (!breakPagination && state.get() !is ListState.LoadingPage) {
            page++
            isPagination = true
            loadListItem()
        }
    }

    fun search(searchText: String, searchFor: String) {
        coroutineScope.launch {
            delay(2000)  //debounce timeOut
            if (searchText != searchFor) return@launch
            nameSearch = searchText
            loadListItem()
        }
    }

    fun searchReset() {
        page = 1
        breakPagination = false
        listItem.clear()
        clearListMutableSingleLiveEvent.value = true
    }

    fun clearSearch() {
        searchReset()
        nameSearch = ""
        loadListItem()
    }

    private fun loadListItem() {
        handlerLoading()

        coroutineScope.launch {
            try {
                val list = listUseCase.execute(page, perPage, nameSearch, themeSelected)
                handlerSuccess(list)
            } catch (e: Exception) {
                e.printStackTrace()
                state.set(ListState.Error)
            }
        }
    }

    private fun handlerLoading() {
        when {
            isPagination -> state.set(ListState.LoadingPage)
            nameSearch.isNotBlank() -> state.set(ListState.LoadingSearch)
            listItem.isEmpty() -> state.set(ListState.Loading)
        }
    }

    private fun handlerSuccess(list: List<ItemModel>) {
        listItem.addAll(list)
        listMutableLiveData.postValue(list)
        state.set(ListState.Success)
        treatPage(list.size)
        totalShowing.set(listItem.size)
        isPagination = false
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