package tech.coner.crispyfish.test

import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.model.ClassDefinition
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

    object Thscc2017 : ClassDefinitions("thscc/2017/class2017_thscc.def") {
        val nov = ClassDefinition(
                abbreviation = "NOV",
                name = "Novice",
                groupName = "Novice",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
        val x = ClassDefinition(
                abbreviation = "X",
                name = "Pro Class",
                groupName = "",
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
        val cs = ClassDefinition(
                abbreviation = "CS",
                name = "C Street",
                groupName = "Street",
                exclude = false,
                paxed = false,
                paxFactor = BigDecimal.valueOf(810, 3)
        )
        val str = ClassDefinition(
                abbreviation = "STR",
                name = "Street Touring R",
                groupName = "Touring",
                exclude = false,
                paxed = false,
                paxFactor = BigDecimal.valueOf(830, 3)
        )
    }

    object Thscc2018 : ClassDefinitions("thscc/2018/class2018_thscc.def") {
        val nov = ClassDefinition(
                abbreviation = "NOV",
                name = "Novice",
                groupName = "Novice",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
        val x = ClassDefinition(
                abbreviation = "X",
                name = "Pro Class",
                groupName = "Pro Class",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(0, 3)
        )
        val mac = ClassDefinition(
                abbreviation = "MAC",
                name = "Classic American Muscle",
                groupName = "CAM Class",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(1000, 3)
        )
        val of = ClassDefinition(
                abbreviation = "OF",
                name = "Old Farts",
                groupName = "Old Farts",
                exclude = false,
                paxed = true,
                paxFactor = BigDecimal.valueOf(1000, 3)
        )
        val cs = ClassDefinition(
                abbreviation = "CS",
                name = "C Street",
                groupName = "Street",
                exclude = false,
                paxed = false,
                paxFactor = BigDecimal.valueOf(805, 3)
        )
        val str = ClassDefinition(
                abbreviation = "STR",
                name = "Street Touring R",
                groupName = "Touring",
                exclude = false,
                paxed = false,
                paxFactor = BigDecimal.valueOf(823, 3)
        )
        val camc = ClassDefinition(
                abbreviation = "CAM-C",
                name = "Classic American C",
                groupName = "Classic American",
                exclude = false,
                paxed = false,
                paxFactor = BigDecimal.valueOf(816, 3)
        )
    }

    object Thscc2019 : ClassDefinitions("thscc/2019/class2019_thscc.def")

    private fun resource(relativeFilePath: String): File {
        val file = File(javaClass.getResource("/tech/coner/crispyfish/test/ClassDefinitions/$relativeFilePath").toURI())
        check(file.exists()) { "test class definition file does not exist: $relativeFilePath" }
        return file
    }
}