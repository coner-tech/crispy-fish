package org.coner.crispyfish.domain

data class Result(
        var position: Int = 0,
        var driver: Driver? = null,
        var run: Run? = null
)
