package tech.coner.crispyfish

import tech.coner.crispyfish.internal.CrispyFishClassDefinitionsImpl
import tech.coner.crispyfish.internal.CrispyFishEventImpl
import tech.coner.crispyfish.internal.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.internal.filetype.eventcontrolfile.EventControlFile
import java.nio.file.Path

object CrispyFish {

    /**
     * Construct a CrispyFishClassDefinitions with a given path, with which you can query the class definitions therein
     *
     * @param defPath a Path pointing to the .def file containing the class definitions
     *
     * @return A CrispyFishClassDefinitions pointed to the given path
     */
    fun classDefinitions(defPath: Path): CrispyFishClassDefinitions {
        return CrispyFishClassDefinitionsImpl(ClassDefinitionFile(defPath))
    }

    /**
     * Construct a CrispyFishEvent with a given path, with which you can query the event's registrations, runs, etc
     *
     * @param ecfPath a Path pointing to the .ecf file for the event
     *
     * @return A CrispyFishEvent pointed to the given path
     */
    fun event(ecfPath: Path): CrispyFishEvent {
        return CrispyFishEventImpl(EventControlFile(ecfPath))
    }
}