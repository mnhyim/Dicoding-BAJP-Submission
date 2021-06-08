package com.mnhyim.moviecatalog.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowsDiscoverResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<ShowResponse>,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("total_results")
    val totalResults: Int
)