package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<StagingLogRow>.timestamp() = prop(StagingLogRow::timestamp)
fun Assert<StagingLogRow>.sequenceRow() = prop(StagingLogRow::stagingRunIndex)
fun Assert<StagingLogRow>.stagingRun() = prop(StagingLogRow::stagingRun)
