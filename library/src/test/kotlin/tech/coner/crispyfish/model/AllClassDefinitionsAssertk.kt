package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<AllClassDefinitions>.categories() = prop(AllClassDefinitions::categories)
fun Assert<AllClassDefinitions>.handicaps() = prop(AllClassDefinitions::handicaps)
