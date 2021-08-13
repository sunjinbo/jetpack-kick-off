package com.sample.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class MovieViewModel : ViewModel() {
    var moviePageList: LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(MovieDataSource.PER_PAGE)
            .setPrefetchDistance(3)
            .setInitialLoadSizeHint(MovieDataSource.PER_PAGE * 4)
            .setMaxSize(65536 * MovieDataSource.PER_PAGE)
            .build()
        moviePageList = LivePagedListBuilder(MovieDataSourceFactory(), config).build()
    }
}