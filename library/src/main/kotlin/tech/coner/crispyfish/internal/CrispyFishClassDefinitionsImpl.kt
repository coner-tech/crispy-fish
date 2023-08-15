package tech.coner.crispyfish.internal

import tech.coner.crispyfish.CrispyFishClassDefinitions
import tech.coner.crispyfish.filetype.ClassDefinitionFile
import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionMapper
import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionReader
import tech.coner.crispyfish.internal.repository.ClassDefinitionRepository
import tech.coner.crispyfish.model.AllClassDefinitions

internal class CrispyFishClassDefinitionsImpl(
    private val classDefinitionFile: ClassDefinitionFile
) : CrispyFishClassDefinitions {

    override fun queryAllClassDefinitions(): AllClassDefinitions {
        return ClassDefinitionRepository(
            ClassDefinitionMapper(
                ClassDefinitionReader(
                    classDefinitionFile
                )
            )
        )
            .getAllClassDefinitions()
    }
}