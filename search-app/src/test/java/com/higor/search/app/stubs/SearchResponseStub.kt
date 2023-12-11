package com.higor.search.app.stubs

import com.higor.search.app.data.dto.SearchItemResponse
import com.higor.search.app.data.dto.SearchMediaResponse
import com.higor.search.app.data.dto.SearchResponse

internal object SearchResponseStub {

    fun create() = SearchResponse(
        title = "FAKE_TITLE",
        link = "FAKE_LINK",
        description = "FAKE_DESCRIPTION",
        modified = "FAKE_MODIFIED",
        generator = "FAKE_GENERATOR",
        items = listOf(createItem())
    )

    private fun createItem() = SearchItemResponse(
        title = "FAKE_TITLE_ITEM",
        link = "FAKE_LINK_ITEM",
        media = SearchMediaResponse(link = "FAKE_MEDIA_ITEM"),
        date_taken = "FAKE_DATE_TAKEN_ITEM",
        description = HtmlStub.create(),
        published = "FAKE_PUBLISHED_ITEM",
        author = "FAKE_AUTHOR_ITEM",
        author_id = "FAKE_IO_ITEM",
        tags = "FAKE_TAG_ITEM",
    )
}
