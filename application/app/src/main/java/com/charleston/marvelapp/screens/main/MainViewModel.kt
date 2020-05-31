package com.charleston.marvelapp.screens.main

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charleston.domain.interactor.ListEventUseCase
import com.charleston.domain.interactor.ListThemesUseCase
import com.charleston.domain.model.ItemModel
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.core.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val listThemesUseCase: ListThemesUseCase,
    private val listEventUseCase: ListEventUseCase
) : ViewModel() {

    private lateinit var themeSelected: ThemeModel
    private var coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val listThemedMutableLiveData = MutableLiveData<List<ThemeModel>>()
    val listThemeLiveData = listThemedMutableLiveData

    private val listEventMutableLiveData = MutableLiveData<List<ItemModel>>()
    val listEventLiveData = listEventMutableLiveData

    private val stateMutableSingleEvent = SingleLiveEvent<MainState>()
    val stateSingleEvent = stateMutableSingleEvent

    val state = ObservableField<MainState>()

    init {
        listThemes()
        listEvents()
    }

    fun selectTheme(themeSelected: ThemeModel) {
        this.themeSelected = themeSelected
        stateMutableSingleEvent.postValue(MainState.StartScreenList(themeSelected))
    }

    private fun listThemes() {
        coroutineScope.launch {
            val list = listThemesUseCase.execute()
            listThemedMutableLiveData.postValue(list)
        }
    }

    private fun listEvents() {
        state.set(MainState.LoadingEvent)
        coroutineScope.launch {
            val list = listEventUseCase.execute()
            listEventMutableLiveData.postValue(list)
            state.set(MainState.SuccessListEvent)
        }
    }
}