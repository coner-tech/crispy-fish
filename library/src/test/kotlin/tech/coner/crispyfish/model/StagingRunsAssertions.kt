package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<StagingRun>.stagingLineRegistration() = prop(StagingRun::stagingRegistration)
fun Assert<StagingRun>.run() = prop(StagingRun::run)