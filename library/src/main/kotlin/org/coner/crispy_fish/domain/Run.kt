package org.coner.crispy_fish.domain

import org.coner.crispy_fish.domain.PenaltyType

import java.time.Duration

data class Run(
        var rawTime: Duration? = null,
        var paxTime: Duration? = null,
        var penaltyType: PenaltyType? = null,
        var cones: Int? = null,
        var timeScored: Duration? = null,
        var timeScratchAsString: String? = null,
        var timeScratchAsDuration: Duration? = null
)
