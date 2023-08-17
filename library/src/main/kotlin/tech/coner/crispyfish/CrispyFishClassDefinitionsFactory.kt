package tech.coner.crispyfish

import java.nio.file.Path

fun interface CrispyFishClassDefinitionsFactory {

    /**
     * Construct a CrispyFishClassDefinitions with a given path, with which you can query the class definitions therein
     *
     * @param defPath a Path pointing to the .def file containing the class definitions
     *
     * @return A CrispyFishClassDefinitions pointed to the given path
     */
    operator fun invoke(defPath: Path): CrispyFishClassDefinitions
}