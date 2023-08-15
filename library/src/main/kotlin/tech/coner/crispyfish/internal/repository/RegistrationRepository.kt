package tech.coner.crispyfish.internal.repository

import tech.coner.crispyfish.internal.filetype.registration.RegistrationLineColumnReader
import tech.coner.crispyfish.mapper.RegistrationMapper
import tech.coner.crispyfish.model.AllRegistrations
import tech.coner.crispyfish.model.Registration
import tech.coner.crispyfish.model.RegistrationsBySignage

internal class RegistrationRepository(
    private val registrationReader: RegistrationLineColumnReader,
    private val registrationMapper: RegistrationMapper,
) {

    fun getAllRegistrations(): AllRegistrations {
        return registrationReader.registrationLines.indices
            .map { index -> registrationMapper.toRegistration(index) }
            .let(::AllRegistrations)
    }

    fun getRegistrationsBySignage(allRegistrations: AllRegistrations): RegistrationsBySignage {
        return allRegistrations.value
            .associateBy(Registration::signage)
            .let(::RegistrationsBySignage)
    }
}