package com.example.recipeapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.usecase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase
): ViewModel() {

    private val _categoryState: MutableState<GetCategoryUseCase.Result> = mutableStateOf(
        GetCategoryUseCase.Result.Loading
    )
    val categoryState: State<GetCategoryUseCase.Result> = _categoryState

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            _categoryState.value = getCategoryUseCase.fetch()
        }
    }


}