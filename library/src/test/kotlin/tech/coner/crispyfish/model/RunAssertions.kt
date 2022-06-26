package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<Run>.number() = prop(Run::number)
fun Assert<Run>.rawTime() = prop(Run::rawTime)
fun Assert<Run>.paxTime() = prop(Run::paxTime)
fun Assert<Run>.penaltyType() = prop(Run::penaltyType)
fun Assert<Run>.cones() = prop(Run::cones)
fun Assert<Run>.timeScored() = prop(Run::timeScored)
fun Assert<Run>.timeScratchAsString() = prop(Run::timeScratchAsString)
fun Assert<Run>.timeScratchAsDuration() = prop(Run::timeScratchAsDuration)