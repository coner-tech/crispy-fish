package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.prop


fun Assert<ClassDefinition?>.hasAbbreviation(expected: String) {
    isNotNull()
        .prop(ClassDefinition::abbreviation).isEqualTo(expected)
}