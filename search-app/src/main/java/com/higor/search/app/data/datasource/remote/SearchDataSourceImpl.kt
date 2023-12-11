package com.higor.search.app.data.datasource.remote

internal class SearchDataSourceImpl(private val api: SearchApi) : SearchDataSource {
    override suspend fun searchImages(searchText: String) =
        api.getImages(getBaseOptions(searchText))

    private fun getBaseOptions(tag: String): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map[FORMAT] = FORMAT_VALUE
        map[JSON_CALLBACK] = JSON_CALLBACK_VALUE
        map[TAG] = tag
        return map.toMap()
    }

    private companion object {
        const val FORMAT = "format"
        const val FORMAT_VALUE = "json"
        const val JSON_CALLBACK = "nojsoncallback"
        const val JSON_CALLBACK_VALUE = "1"
        const val TAG = "tags"
    }
}
