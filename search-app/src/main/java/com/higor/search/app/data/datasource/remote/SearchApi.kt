package com.higor.search.app.data.datasource.remote

import com.higor.search.app.data.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface SearchApi {

    @GET(OP_KEY_SEARCH)
    suspend fun getImages(
        @QueryMap options: Map<String, String?>,
    ): SearchResponse

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/feeds/"
        const val OP_KEY_SEARCH = "photos_public.gne"
    }
}
