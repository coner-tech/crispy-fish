package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<Classing>.category() = prop(Classing::category)

fun Assert<Classing>.handicap() = prop(Classing::handicap)