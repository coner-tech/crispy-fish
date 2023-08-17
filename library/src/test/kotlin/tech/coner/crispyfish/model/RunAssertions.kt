package tech.coner.crispyfish.model

import assertk.Assert
import assertk.all
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import assertk.assertions.support.expected

fun Assert<Run>.number() = prop(Run::number)
fun Assert<Run>.rawTime() = prop(Run::rawTime)
fun Assert<Run>.paxTime() = prop(Run::paxTime)
fun Assert<Run>.penaltyType() = prop(Run::penaltyType)
fun Assert<Run>.cones() = prop(Run::cones)
fun Assert<Run>.timeScored() = prop(Run::timeScored)
fun Assert<Run>.timeScratchAsString() = prop(Run::timeScratchAsString)
fun Assert<Run>.timeScratchAsDuration() = prop(Run::timeScratchAsDuration)

fun Assert<Run>.isBlank() = all {
    number().isNull()
    rawTime().isNull()
    paxTime().isNull()
    penaltyType().isEqualTo(PenaltyType.CLEAN)
    cones().isNull()
    timeScored().isNull()
    timeScratchAsString().isNull()
    timeScratchAsDuration().isNull()
}

fun Assert<Run>.isFinished() = all {
    number().isNotNull()
    rawTime().isNotNull()
    penaltyType().isNotNull()
    given {
        if (it.penaltyType == PenaltyType.CONE) {
            if (it.cones != null) return@given
            expected("run with cone penalty type to have cones")
        }
    }
}