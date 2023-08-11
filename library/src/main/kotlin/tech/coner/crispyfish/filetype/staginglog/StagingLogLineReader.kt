package tech.coner.crispyfish.filetype.staginglog

import tech.coner.crispyfish.datatype.underscorepairs.UnderscorePairReader

class StagingLogLineReader(
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