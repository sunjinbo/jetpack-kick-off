package com.sample.jetpack

import com.google.gson.annotations.SerializedName

class Movies {
    var count: Int = 0
    var start: Int = 0
    var total: Int = 0

    @SerializedName("subjects")
    var movieList: List<Movie> = ArrayList()
}
