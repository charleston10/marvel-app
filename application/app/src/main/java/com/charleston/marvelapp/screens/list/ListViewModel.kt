package com.charleston.marvelapp.screens.list

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListUseCase
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.core.SingleLiveEvent
import kotlinx.coroutines.*
import java.lang.Exception

class ListViewModel(
    private val listUseCase: ListUseCase
) : ViewModel() {

    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val listItem: ArrayList<ItemModel> = arrayListOf()
    private lateinit var themeSelected: ThemeModel

    private var initialized = false

    private var page = 1
    private var perPage = 10
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

    private fun loadListItem() {
        handlerLoading()

        try {
            coroutineScope.launch {
                val list = listUseCase.execute(page, perPage, nameSearch, themeSelected)
                handlerSuccess(list)
            }
        } catch (e: Exception) {
            state.set(ListState.Error)
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

    private fun searchReset() {
        page = 1
        breakPagination = false
        listItem.clear()
        clearListMutableSingleLiveEvent.value = true
    }

    private fun clearSearch(){
        searchReset()
        nameSearch = ""
        loadListItem()
    }

    val watcherSearch = object : TextWatcher {
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            if (searchFor.isEmpty() && searchText.isNotBlank()) {//conditional for know first search (clear list)
                searchReset()
            } else if (searchText.isBlank()) {//conditional for edit text was cleared
                clearSearch()
            }

            searchFor = searchText

            coroutineScope.launch {
                delay(1000)  //debounce timeOut
                if (searchText != searchFor) return@launch
                nameSearch = searchText
                loadListItem()
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }
}