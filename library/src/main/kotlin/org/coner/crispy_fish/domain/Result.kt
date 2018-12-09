package org.coner.crispy_fish.domain

import org.crispy_fish.domain.payload.Driver
import org.crispy_fish.domain.payload.Run

data class Result(
        var position: Int = 0,
        var driver: Driver? = null,
        var run: Run? = null
)
