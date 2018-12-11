package org.coner.crispy_fish.domain

enum class PenaltyType(val shouldOmitRunFromResults: Boolean = false) {
    CONE,
    DID_NOT_FINISH,
    DISQUALIFIED,
    RERUN(shouldOmitRunFromResults = true),
    CLEAN
}
