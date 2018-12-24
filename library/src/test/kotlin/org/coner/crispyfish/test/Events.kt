package org.coner.crispyfish.test

import org.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File

sealed class Events(
        val classDefinitions: ClassDefinitions,
        ecfFile: String,
        conePenalty: Int = 2,
        twoDayEvent: Boolean = false
) {

    val eventControlFile = EventControlFile(
            file = resource(ecfFile),
            classDefinitionFile = classDefinitions.file,
            conePenalty = conePenalty,
            isTwoDayEvent = twoDayEvent
    )

    object Thscc2016Points1Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points1/2016-03-05 points autox 1 danville.ecf"
    )

    object Thscc2016Points2Cary : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points2/2016-03-18 points autox 2 cary.ecf"
    )

    object Thscc2016Points3Danville : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points3/2016 Points Event 3 Danville.ecf"
    )

    object Thscc2016Points9Cary : Events(
            classDefinitions = ClassDefinitions.Thscc2016,
            ecfFile = "thscc/2016/points9/2016-09-17 points autox 9 cary.ecf"
    )

    private fun resource(relativeFilePath: String): File {
        val absolutePath = "/org/coner/crispyfish/test/Events/$relativeFilePath"
        val resourceURL = javaClass.getResource(absolutePath)
        val file = File(resourceURL.toURI())
        check(file.exists()) { "test event resource does not exist: $relativeFilePath" }
        return file
    }
}