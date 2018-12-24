package org.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop

fun Assert<Registration>.hasCategoryNull() {
    assert(actual).prop(Registration::category).isNull()
}

fun Assert<Registration>.hasCategoryAbbreviation(expected: String) {
    assert(actual).prop(Registration::category).prop(ClassDefinition::abbreviation).isEqualTo(expected)
}

fun Assert<Registration>.hasHandicapAbbreviation(expected: String) {
    assert(actual).prop(Registration::handicap).prop(ClassDefinition::abbreviation).isEqualTo(expected)
}

fun Assert<Registration>.hasNumber(expected: String) {
    assert(actual).prop(Registration::number).isEqualTo(expected)
}

fun Assert<Registration>.hasFirstName(expected: String) {
    assert(actual).prop(Registration::firstName).isEqualTo(expected)
}

fun Assert<Registration>.hasLastName(expected: String) {
    assert(actual).prop(Registration::lastName).isEqualTo(expected)
}

fun Assert<Registration>.hasCarModel(expected: String) {
    assert(actual).prop(Registration::carModel).isEqualTo(expected)
}

fun Assert<Registration>.hasCarColor(expected: String) {
    assert(actual).prop(Registration::carColor).isEqualTo(expected)
}