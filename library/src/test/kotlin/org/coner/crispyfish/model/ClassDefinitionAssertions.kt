package org.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.prop

fun Assert<ClassDefinition?>.hasAbbreviation(expected: String) {
    assert(actual).prop(ClassDefinition::abbreviation).isEqualTo(expected)
}