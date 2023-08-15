package tech.coner.crispyfish.model

import assertk.Assert
import assertk.assertions.prop

fun Assert<AllStagingRuns>.value() = prop((AllStagingRuns::value))