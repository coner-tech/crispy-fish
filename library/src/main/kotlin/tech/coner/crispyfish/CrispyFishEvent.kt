package tech.coner.crispyfish

import tech.coner.crispyfish.filetype.EventControlFile
import tech.coner.crispyfish.internal.CrispyFishEventImpl
import tech.coner.crispyfish.model.*

interface CrispyFishEvent {


    fun queryAllRegistrations(
        allClassDefinitions: AllClassDefinitions
    ): AllRegistrations

    fun queryAllRegistrationsBySignage(
        allClassDefinitions: AllClassDefinitions,
        allRegistrations: AllRegistrations
    ): RegistrationsBySignage

    fun queryAllStagingRuns(
        eventDay: EventDay,
        allClassDefinitions: AllClassDefinitions,
        registrationsBySignage: RegistrationsBySignage
    ): AllStagingRuns

    fun queryAllStagingLogRows(
        eventDay: EventDay,
        allClassDefinitions: AllClassDefinitions,
        registrationsBySignage: RegistrationsBySignage
    ): AllStagingLogRows

    companion object {
        fun factory(eventControlFile: EventControlFile): CrispyFishEvent {
            return CrispyFishEventImpl(eventControlFile)
        }
    }
}