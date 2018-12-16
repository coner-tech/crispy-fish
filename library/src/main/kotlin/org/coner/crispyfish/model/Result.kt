package org.coner.crispyfish.model

data class Result(
        var position: Int = 0,
        var driver: Registration? = null,
        var run: Run? = null
)
