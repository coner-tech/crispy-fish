package org.coner.crispy_fish.filetype.staging

import org.coner.crispy_fish.datatype.underscore_pairs.UnderscorePairReader

class SimpleStringStagingLineReader(
        private val underscorePairReader: UnderscorePairReader<String>
) : StagingLineReader<String> {

    override fun getRunNumber(line: String) = underscorePairReader.get(line, StagingLineKeys.RUN)

    override fun getRegisteredDriverClass(line: String) = underscorePairReader.get(line, StagingLineKeys.CLASS)

    override fun getRegisteredDriverNumber(line: String) = underscorePairReader.get(line, StagingLineKeys.NUMBER)

    override fun getRunRawTime(line: String) = underscorePairReader.get(line, StagingLineKeys.TM)

    override fun getRunPenalty(line: String) = underscorePairReader.get(line, StagingLineKeys.PENALTY)

    override fun getRegisteredDriverName(line: String) = underscorePairReader.get(line, StagingLineKeys.DRIVER)

    override fun getRegisteredDriverCar(line: String) = underscorePairReader.get(line, StagingLineKeys.CAR)

    override fun getRegisteredDriverCarColor(line: String) = underscorePairReader.get(line, StagingLineKeys.CC)

    override fun getDriverPositionInClassForDay(line: String) = underscorePairReader.get(line, StagingLineKeys.POS)

    override fun getDriverBestTimeOfDay(line: String) = underscorePairReader.get(line, StagingLineKeys.BESTTIME)

    override fun getRunPaxTime(line: String) = underscorePairReader.get(line, StagingLineKeys.PAXED)

    override fun getRunTimestamp(line: String) = underscorePairReader.get(line, StagingLineKeys.TOD)
}
