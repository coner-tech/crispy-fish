package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<StagingRun>.stagingRegistration() = prop(StagingRun::stagingRegistration)
fun Assert<StagingRun>.registration() = prop(StagingRun::registration)
fun Assert<StagingRun>.run() = prop(StagingRun::run)
