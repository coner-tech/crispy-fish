package tech.coner.crispyfish.filetype.classdefinition

import java.io.File

class ClassDefinitionFile(
        val file: File
) {
    fun reader() = ClassDefinitionReader(this)

    fun mapper() = ClassDefinitionMapper(reader())
}