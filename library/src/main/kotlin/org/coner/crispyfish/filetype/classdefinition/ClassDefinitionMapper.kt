package org.coner.crispyfish.filetype.classdefinition

import org.coner.crispyfish.model.ClassDefinition

class ClassDefinitionMapper(val reader: ClassDefinitionReader) {

    fun all() = reader.lines.indices.map { atIndex(it) }

    fun atIndex(index: Int) = ClassDefinition(
            abbreviation = reader.readAbbreviation(index),
            name = reader.readName(index),
            groupName = reader.readGroupName(index),
            paxFactor = reader.readPaxFactor(index).toBigDecimal(),
            paxed = reader.readPaxedClass(index).isNotBlank(),
            exclude = reader.readExclude(index).isNotBlank()
    )
}