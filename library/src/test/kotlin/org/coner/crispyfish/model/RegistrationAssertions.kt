package org.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop

fun Assert<Registration>.hasCategoryNull() {
    prop("category", Registration::category).isNull()
}

fun Assert<Registration>.hasCategoryAbbreviation(expected: String) {
    prop("category", Registration::category).isNotNull().hasAbbreviation(expected)
}

fun Assert<Registration>.hasHandicapAbbreviation(expected: String) {
    prop("handicap", Registration::handicap).hasAbbreviation(expected)
}

fun Assert<Registration>.hasNumber(expected: String) {
    prop("number", Registration::number).isEqualTo(expected)
}

fun Assert<Registration>.hasFirstName(expected: String) {
    prop("firstName", Registration::firstName).isEqualTo(expected)
}

fun Assert<Registration>.hasLastName(expected: String) {
    prop("lastName", Registration::lastName).isEqualTo(expected)
}

fun Assert<Registration>.hasCarModel(expected: String) {
    prop("carModel", Registration::carModel).isEqualTo(expected)
}

fun Assert<Registration>.hasCarColor(expected: String) {
    prop("carColor", Registration::carColor).isEqualTo(expected)
}