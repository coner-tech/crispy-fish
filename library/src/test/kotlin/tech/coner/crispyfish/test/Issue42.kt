package tech.coner.crispyfish.test

import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File

object Issue42 {
    private fun resource(relativeFilePath: String): File {
        return File(javaClass.getResource("/tech/coner/crispyfish/query/Issue42Test/$relativeFilePath").toURI())
    }

    val classDefinitionFile by lazy {
        ClassDefinitionFile(
            file = resource("class2022_lscc.def")
        )
    }

    val eventControlFile by lazy {
        EventControlFile(
            file = resource("64-crispy-fish-staging-lines-invalid-signage.ecf"),
            classDefinitionFile = classDefinitionFile,
            isTwoDayEvent = false,
            conePenalty = 2
        )
    }
}
