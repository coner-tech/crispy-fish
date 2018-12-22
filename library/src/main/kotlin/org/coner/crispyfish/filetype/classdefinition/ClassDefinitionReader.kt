package org.coner.crispyfish.filetype.classdefinition

import kotlin.streams.toList

class ClassDefinitionReader(private val classDefinitionFile: ClassDefinitionFile) {

    val lines by lazy {
        classDefinitionFile.file.readLines()
                .parallelStream()
                .map { it.split("\t") }
                .toList()
    }

    private fun read(index: Int, column: ClassDefinitionColumns): String {
        return lines[index][column.index]
    }

    fun readAbbreviation(index: Int) = read(index, ClassDefinitionColumns.ABBREVIATION)

    fun readPaxFactor(index: Int) = read(index, ClassDefinitionColumns.PAX_FACTOR)

    fun readName(index: Int) = read(index, ClassDefinitionColumns.NAME)

    fun readPaxedClass(index: Int) = read(index, ClassDefinitionColumns.PAXED_CLASS)

    fun readExclude(index: Int) = read(index, ClassDefinitionColumns.EXCLUDE)

    fun readGroupName(index: Int) = read(index, ClassDefinitionColumns.GROUP_NAME)

}
