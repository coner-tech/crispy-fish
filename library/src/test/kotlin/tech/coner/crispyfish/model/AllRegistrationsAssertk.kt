package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<AllRegistrations>.value() = prop(AllRegistrations::value)