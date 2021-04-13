package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.prop

fun Assert<RegistrationResult>.hasTime(expected: String) {
    prop("time", RegistrationResult::time).isEqualTo(expected)
}

fun Assert<RegistrationResult>.hasPosition(expected: Int?) {
    prop("position", RegistrationResult::position).isEqualTo(expected)
}