package tech.coner.crispyfish

import java.nio.file.Path

fun interface CrispyFishEventFactory {

    /**
     * Construct a CrispyFishEvent with a given path, with which you can query the event's registrations, runs, etc
     *
     * @param ecfPath a Path pointing to the .ecf file for the event
     *
     * @return A CrispyFishEvent pointed to the given path
     */
    operator fun invoke(ecfPath: Path): CrispyFishEvent
}