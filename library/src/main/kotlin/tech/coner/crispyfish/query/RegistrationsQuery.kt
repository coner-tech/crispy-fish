package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.filetype.registration.RegistrationFileException
import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.Registration

class RegistrationsQuery(
        private val eventControlFile: EventControlFile,
        private val categories: List<ClassDefinition>,
        private val handicaps: List<ClassDefinition>
) {

    fun query(): List<Registration> {
        val registrationFile = eventControlFile.registrationFile()
        val reader = registrationFile.columnReader()
        val mapper = registrationFile.mapper()
        val registrations = mutableListOf<Registration>()
        for (i in reader.registrationLines.indices) {
            registrations += mapper.toRegistration(
                categories = categories,
                handicaps = handicaps,
                reader = reader,
                index = i
            )
        }
        return registrations
    }
}