package tech.coner.crispyfish.internal.filetype.staginglog

import tech.coner.crispyfish.internal.datatype.underscorepairs.UnderscorePairReader

internal class StagingLogLineReader(
    private val underscorePairReader: UnderscorePairReader<String>
) {

    private val timestampPattern = Regex("^(\\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2})")

    fun getSequenceRow(line: String): String? {
        return underscorePairReader.get(line, StagingLogLineKeys.SEQUENCE_ROW)
    }

    fun getTimestamp(line: String): String? {
        return timestampPattern.find(line)?.groupValues?.first()
    }
}