package org.coner.crispyfish.domain

enum class PenaltyType(val shouldOmitRunFromResults: Boolean = false) {
    CONE,
    DID_NOT_FINISH,
    DISQUALIFIED,
    RERUN(shouldOmitRunFromResults = true),
    CLEAN
}
