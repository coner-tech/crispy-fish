package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<Signage>.classing() = prop(Signage::classing)

fun Assert<Signage>.number() = prop(Signage::number)