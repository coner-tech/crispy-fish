package tech.coner.crispyfish.internal.filetype.staging

internal interface StagingLineReader<L> {

    fun getRegisteredDriverClass(line: L): String?

    fun getRegisteredDriverNumber(line: L): String?

    fun getRegisteredDriverName(line: L): String?

    fun getRegisteredDriverCar(line: L): String?

    fun getRegisteredDriverCarColor(line: L): String?

    fun getRunNumber(line: L): String?

    fun getRunRawTime(line: L): String?

    fun getRunPaxTime(line: L): String?

    fun getRunPenalty(line: L): String?

    fun getRunTimestamp(line: L): String?

    fun getDriverPositionInClassForDay(line: L): String?

    fun getDriverBestTimeOfDay(line: L): String?
}
