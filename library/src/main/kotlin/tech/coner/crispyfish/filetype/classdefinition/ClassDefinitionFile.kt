package tech.coner.crispyfish.filetype.classdefinition

import java.nio.file.Path

class ClassDefinitionFile(
        val file: Path
) {
    fun reader() = ClassDefinitionReader(this)

    fun mapper() = ClassDefinitionMapper(reader())
}