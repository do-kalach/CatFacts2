package com.example.catfacts.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.catfacts.data.repository.CatFactRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatFactsViewModel @Inject constructor(
    catFactRepo: CatFactRepo
) : ViewModel() {
    val pagerNoRemoteMediator = catFactRepo
        .getCatFactPagerWithoutRemoteMediator()
        .cachedIn(viewModelScope)

    val currentPage = catFactRepo.currentPage
}