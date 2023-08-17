package tech.coner.crispyfish.model

data class StagingLogRow(
    val timestamp: String,
    val stagingRunIndex: Int,
    val stagingRun: StagingRun,
)
