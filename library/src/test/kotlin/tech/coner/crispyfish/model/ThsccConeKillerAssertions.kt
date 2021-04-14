package tech.coner.crispyfish.model

import assertk.Assert
import assertk.all
import assertk.assertions.isEqualTo
import assertk.assertions.prop


fun Assert<ThsccConeKillerResult>.hasPosition(expected: Int) {
    prop("position", ThsccConeKillerResult::position).isEqualTo(expected)
}

fun Assert<ThsccConeKillerResult>.registration(body: Assert<Registration>.() -> Unit) {
    prop("registration", ThsccConeKillerResult::registration).all(body)
}

fun Assert<ThsccConeKillerResult>.conedRuns(body: Assert<List<RegistrationRun>>.() -> Unit) {
    prop("conedRuns", ThsccConeKillerResult::conedRuns).all(body)
}