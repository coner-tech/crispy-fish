package tech.coner.crispyfish

import tech.coner.crispyfish.internal.CrispyFishClassDefinitionsImpl
import tech.coner.crispyfish.internal.CrispyFishEventImpl
import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.internal.filetype.eventcontrolfile.EventControlFile

object CrispyFish {

    val classDefinitions = CrispyFishClassDefinitionsFactory {
        CrispyFishClassDefinitionsImpl(ClassDefinitionFile(it))
    }

    val event = CrispyFishEventFactory {
        CrispyFishEventImpl(EventControlFile(it))
    }
}