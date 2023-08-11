package tech.coner.crispyfish.model

data class StagingLogRow(
    val timestamp: String,
    val sequenceRow: Int,
    val stagingRun: StagingRun,
)
