package org.coner.crispyfish.test

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.model.ClassDefinition
import java.io.File
import java.math.BigDecimal

sealed class ClassDefinitions(val defFile: String) {

    val file = ClassDefinitionFile(
            file = resource(defFile)
    )

    object Thscc2016 : ClassDefinitions("thscc/2016/class2016_thscc.def") {
        val cs = ClassDefinition(
                abbreviation = "CS",
                name = "C Street",
                groupName = "Street",
                paxFactor = BigDecimal.valueOf(819, 3),
                exclude = false,
                paxed = false
        )
        val str = ClassDefinition(
                abbreviation = "STR",
                name = "Street Touring R",
                groupName = "Touring",
                paxFactor = BigDecimal.valueOf(841, 3),
                exclude = false,
                paxed = false
        )
        val x = ClassDefinition(
                abbreviation = "X",
                name = "X-Pro Pax",
                groupName = "X Pro Pax",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
        val nov = ClassDefinition(
                abbreviation = "NOV",
                name = "Novice",
                groupName = "Novice",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
        val of = ClassDefinition(
                abbreviation = "OF",
                name = "Old Farts",
                groupName = "Old Farts",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
    }

    private fun resource(relativeFilePath: String): File {
        val file = File(javaClass.getResource("/org/coner/crispyfish/test/ClassDefinitions/$relativeFilePath").toURI())
        check(file.exists()) { "test class definition file does not exist: $relativeFilePath" }
        return file
    }
}