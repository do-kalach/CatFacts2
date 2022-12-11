package com.example.catfacts.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catfacts.data.model.CatFact
import com.example.catfacts.service.WebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CatFactRepo @Inject constructor(
    private val webService: WebService
) {

    private val _currentPage = MutableStateFlow("0")
    val currentPage = _currentPage.asStateFlow()

    fun getCatFactPagerWithoutRemoteMediator(): Flow<PagingData<CatFact>> =
        Pager(
            config = PagingConfig(pageSize = 1)
        ) {
            CatFactPagingSource(webService = webService) { page ->
                _currentPage.value = "$page"
            }
        }.flow

}