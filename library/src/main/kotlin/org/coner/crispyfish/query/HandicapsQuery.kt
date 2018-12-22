package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.model.ClassDefinition

class HandicapsQuery(
        private val classDefinitionFile: ClassDefinitionFile
) {
    fun query() = classDefinitionFile.mapper()
            .all()
            .filter { !it.paxed }
}