package com.surodeev.currenciestestapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {
    var currentSortState by mutableStateOf(SortVariant.ALPHABET_ASC)

    enum class SortVariant {
        ALPHABET_ASC, ALPHABET_DESC, VALUE_ASC, VALUE_DESC
    }
}