package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile

class CategoriesQuery(
        private val classDefinitionFile: ClassDefinitionFile
) {
    fun query() = classDefinitionFile.mapper()
                .all()
                .filter { it.paxed }
}