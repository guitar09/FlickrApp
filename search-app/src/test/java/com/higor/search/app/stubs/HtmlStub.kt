package com.higor.search.app.stubs

internal object HtmlStub {
    fun create() =
        " <p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/swagshamu\\/\\\">TursiopsTobie<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/swagshamu\\/53372019478\\/\\\" title=\\\"American Porcupine - Orange County Zoo\\\"><img src=\\\"https:\\/\\/live.staticflickr.com\\/65535\\/53372019478_356ee71a56_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"American Porcupine - Orange County Zoo\\\" \\/><\\/a><\\/p> <p>Please this info is just an example<\\/p> "
            .replace("\\\"", "\"").replace("\\/", "/")
}