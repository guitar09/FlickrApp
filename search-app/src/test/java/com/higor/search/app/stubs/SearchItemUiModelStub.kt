package com.higor.search.app.stubs

import com.higor.search.app.ui.model.SearchItemUiModel

object SearchItemUiModelStub {

    fun create() = SearchItemUiModel(
        title = "FAKE_TITLE",
        imageUrl = "FAKE_URL",
        description = "FAKE_DESCRIPTION",
        author = "FAKE_AUTHOR",
        width = 100,
        height = 200
    )
}