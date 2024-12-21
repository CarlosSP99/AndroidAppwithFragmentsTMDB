package com.utad.rvwithmvvc.data.model

import com.google.gson.annotations.SerializedName

data class PageModel(
    @SerializedName (value="page")val page: Int,
    @SerializedName (value="total_pages")val totalPages: Int=242,
    @SerializedName (value="results")val results: List<MovieModel>,
)
