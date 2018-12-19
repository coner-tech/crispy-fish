package org.coner.crispyfish.test

import org.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File

sealed class Events(ecfFile: String, conePenalty: Int = 2, twoDayEvent: Boolean = false) {

    val eventControlFile = EventControlFile(
            path = resource(ecfFile).toPath(),
            conePenalty = conePenalty,
            isTwoDayEvent = twoDayEvent
    )

    object Thscc2016Points3Danville : Events("thscc/2016/2016 Points Event 3 Danville.ecf")

    private fun resource(relativeFilePath: String): File {
        val file = File(javaClass.getResource("/org/coner/crispyfish/test/Events/$relativeFilePath").toURI())
        check(file.exists()) { "test event resource does not exist: $relativeFilePath" }
        return file
    }
}