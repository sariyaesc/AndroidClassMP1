package com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgeromo.androidClassMp1.secondpartial.home.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository = HomeRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(HomeUiState(isLoading = true))
    val ui: StateFlow<HomeUiState> = _ui

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            try {
                val events = repository.getEvents().getOrThrow()
                // Mapear por categoría
                val categorias = events.groupBy { it.category }
                _ui.value = HomeUiState(categorias = categorias, isLoading = false)
            } catch (e: Exception) {
                _ui.value = HomeUiState(isLoading = false, error = e.localizedMessage)
            }
        }
    }
}

