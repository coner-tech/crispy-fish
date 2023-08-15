package tech.coner.crispyfish.internal.repository

import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionMapper
import tech.coner.crispyfish.model.AllClassDefinitions
import tech.coner.crispyfish.model.ClassDefinition

internal class ClassDefinitionRepository(
    private val mapper: ClassDefinitionMapper
) {

    fun getAllClassDefinitions(): AllClassDefinitions {
        val everyClassDefinition = mapper.all()
        return AllClassDefinitions(
            categories = getCategories(everyClassDefinition),
            handicaps = getHandicaps(everyClassDefinition)
        )
    }

    private fun getCategories(
        everyClassDefinition: List<ClassDefinition>
    ): List<ClassDefinition> {
        return everyClassDefinition.filter { it.paxed }
    }

    private fun getHandicaps(everyClassDefinition: List<ClassDefinition>): List<ClassDefinition> {
        return everyClassDefinition.filter { !it.paxed }
    }
}