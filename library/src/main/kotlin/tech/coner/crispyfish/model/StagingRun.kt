package tech.coner.crispyfish.model

data class StagingRun(
    val stagingRegistration: StagingRegistration?,
    val registration: Registration?,
    val run: Run?
)