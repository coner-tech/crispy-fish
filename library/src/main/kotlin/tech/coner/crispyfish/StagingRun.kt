package tech.coner.crispyfish

import tech.coner.crispyfish.model.Run
import tech.coner.crispyfish.model.StagingLineRegistration

data class StagingRun(
    val stagingLineRegistration: StagingLineRegistration?,
    val run: Run?
)
