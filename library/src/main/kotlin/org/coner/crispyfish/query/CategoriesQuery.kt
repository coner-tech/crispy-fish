package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile

class CategoriesQuery(
        private val classDefinitionFile: ClassDefinitionFile
) {
    fun query() = classDefinitionFile.mapper()
                .all()
                .filter { it.paxed }
}