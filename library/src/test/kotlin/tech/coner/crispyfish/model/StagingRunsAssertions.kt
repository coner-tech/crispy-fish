package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop
import tech.coner.crispyfish.StagingRun

fun Assert<StagingRun>.stagingLineRegistration() = prop(StagingRun::stagingLineRegistration)
fun Assert<StagingRun>.run() = prop(StagingRun::run)