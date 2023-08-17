package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<AllStagingLogRows>.value() = prop(AllStagingLogRows::value)