package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.registration.RegistrationLineColumnReader
import tech.coner.crispyfish.mapper.RegistrationMapper
import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.Registration

internal class RegistrationsQuery(
    private val reader: RegistrationLineColumnReader,
    private val mapper: RegistrationMapper,
    private val categories: List<ClassDefinition>,
    private val handicaps: List<ClassDefinition>
) {

    fun query(): List<Registration> {
        val registrations = mutableListOf<Registration>()
        for (i in reader.registrationLines.indices) {
            registrations += mapper.toRegistration(
                index = i
            )
        }
        return registrations
    }
}