package org.coner.crispyfish.test

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import java.io.File

sealed class ClassDefinitions(val defFile: String) {

    val file = ClassDefinitionFile(
            file = resource(defFile)
    )

    object Thscc2016 : ClassDefinitions("thscc/2016/class2016_thscc.def")

    private fun resource(relativeFilePath: String): File {
        val file = File(javaClass.getResource("/org/coner/crispyfish/test/ClassDefinitions/$relativeFilePath").toURI())
        check(file.exists()) { "test class definition file does not exist: $relativeFilePath" }
        return file
    }
}