package tech.coner.crispyfish.internal.filetype.classdefinition

import tech.coner.crispyfish.model.ClassDefinition

internal class ClassDefinitionMapper(val reader: ClassDefinitionReader) {

    fun all(): List<ClassDefinition> {
        val lines = reader.buildLines()
        return lines.value.indices.map { index ->
            ClassDefinition(
                abbreviation = reader.readAbbreviation(lines, index).uppercase(),
                name = reader.readName(lines, index),
                groupName = reader.readGroupName(lines, index),
                paxFactor = reader.readPaxFactor(lines, index).toBigDecimal(),
                paxed = reader.readPaxedClass(lines, index).isNotBlank(),
                exclude = reader.readExclude(lines, index).isNotBlank()
            )
        }
    }
}