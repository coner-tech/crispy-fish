package tech.coner.crispyfish.internal.filetype.classdefinition

import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionColumns.*
import kotlin.io.path.readLines

internal class ClassDefinitionReader(private val classDefinitionFile: ClassDefinitionFile) {

    fun buildLines(): ClassDefinitionLines {
        return classDefinitionFile.file.readLines()
            .asSequence()
            .map { it.split("\t") }
            .toList()
            .let(::ClassDefinitionLines)
    }

    private fun read(lines: ClassDefinitionLines, index: Int, column: ClassDefinitionColumns): String {
        return lines.value[index][column.index]
    }

    fun readAbbreviation(lines: ClassDefinitionLines, index: Int) = read(lines, index, ABBREVIATION)

    fun readPaxFactor(lines: ClassDefinitionLines, index: Int) = read(lines, index, PAX_FACTOR)

    fun readName(lines: ClassDefinitionLines, index: Int) = read(lines, index, NAME)

    fun readPaxedClass(lines: ClassDefinitionLines, index: Int) = read(lines, index, PAXED_CLASS)

    fun readExclude(lines: ClassDefinitionLines, index: Int) = read(lines, index, EXCLUDE)

    fun readGroupName(lines: ClassDefinitionLines, index: Int) = read(lines, index, GROUP_NAME)

}
