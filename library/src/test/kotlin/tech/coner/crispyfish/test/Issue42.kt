package tech.coner.crispyfish.test

import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.toPath

object Issue42 {
    private fun resource(relativeFilePath: String): Path {
        val path = javaClass.getResource("/tech/coner/crispyfish/query/Issue42Test/$relativeFilePath")?.toURI()?.toPath()
        check(path?.exists() == true) { "resource does not exist: $path, likely error in test arrangement or tools" }
        return path!!
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
            isTwoDayEvent = false
        )
    }
}
