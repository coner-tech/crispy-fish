package tech.coner.crispyfish

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
}
