package org.coner.crispyfish.model

enum class PenaltyType(val shouldOmitRunFromResults: Boolean = false) {
    CONE,
    DID_NOT_FINISH,
    DISQUALIFIED,
    RERUN(shouldOmitRunFromResults = true),
    CLEAN
}
