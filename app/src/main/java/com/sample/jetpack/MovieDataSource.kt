package com.sample.jetpack

import androidx.paging.PositionalDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource() : PositionalDataSource<Movie>() {
    companion object {
        const val PER_PAGE = 5
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
        var startPosition = 0

        RetrofitClient.get().getApi()
            .getMovies(startPosition, PER_PAGE)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                   // ignore
                }

                override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                    response?.body()?.let {
                        callback.onResult(it.movieList, it.start, it.total)
                    }
                }
        })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        RetrofitClient.get().getApi()
            .getMovies(params.startPosition, PER_PAGE)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                    // ignore
                }

                override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                    response?.body()?.let {
                        callback.onResult(it.movieList)
                    }
                }
            })
    }
}