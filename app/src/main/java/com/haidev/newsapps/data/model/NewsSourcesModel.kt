package com.haidev.newsapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object NewsSourcesModel {

    data class Response(
        val sources: List<Source>,
        val status: String
    ) {
        @Parcelize
        data class Source(
            val category: String?,
            val country: String?,
            val description: String?,
            val id: String?,
            val language: String?,
            val name: String?,
            val url: String?
        ) : Parcelable
    }
}