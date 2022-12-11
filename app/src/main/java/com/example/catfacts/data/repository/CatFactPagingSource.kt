package com.example.catfacts.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catfacts.data.model.CatFact
import com.example.catfacts.service.WebService
import retrofit2.HttpException
import java.io.IOException

private const val CAT_FACTS_STARTING_PAGE_INDEX = 1

class CatFactPagingSource(
    private val webService: WebService,
    private val pageNumberUpdater: (page: Int) -> Unit
) : PagingSource<Int, CatFact>() {
    override fun getRefreshKey(state: PagingState<Int, CatFact>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatFact> {
        try {
            val position = params.key ?: CAT_FACTS_STARTING_PAGE_INDEX
            pageNumberUpdater(position)

            val response = webService.getCatFactPage(position)
            val catFacts = response.data

            val prevKey = if (position == CAT_FACTS_STARTING_PAGE_INDEX) {
                null
            } else {
                position - 1
            }

            val nextKey = if (catFacts.isEmpty()) {
                null
            } else {
                position + 1
            }

            return LoadResult.Page(
                data = catFacts,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}