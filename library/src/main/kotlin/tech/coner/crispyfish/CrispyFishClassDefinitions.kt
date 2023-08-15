package tech.coner.crispyfish

import tech.coner.crispyfish.filetype.ClassDefinitionFile
import tech.coner.crispyfish.internal.CrispyFishClassDefinitionsImpl
import tech.coner.crispyfish.model.AllClassDefinitions

interface CrispyFishClassDefinitions {

    fun queryAllClassDefinitions(): AllClassDefinitions

    companion object {
        fun factory(classDefinitionFile: ClassDefinitionFile): CrispyFishClassDefinitions {
            return CrispyFishClassDefinitionsImpl(classDefinitionFile)
        }
    }
}