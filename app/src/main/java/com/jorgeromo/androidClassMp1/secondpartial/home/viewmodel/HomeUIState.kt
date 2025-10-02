package com.jorgeromo.androidClassMp1.secondpartial.home.viewmodel

import com.jorgeromo.androidClassMp1.secondpartial.home.model.dto.Event

data class HomeUiState(
    val categorias: Map<String, List<Event>> = emptyMap(), // categoría -> eventos
    val isLoading: Boolean = false,
    val error: String? = null
)