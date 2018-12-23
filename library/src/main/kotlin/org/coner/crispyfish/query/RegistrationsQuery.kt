package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.ecf.EventControlFile
import org.coner.crispyfish.filetype.registration.RegistrationFileException
import org.coner.crispyfish.model.ClassDefinition
import org.coner.crispyfish.model.Registration

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
            try {
                registrations += mapper.toRegistration(
                        categories = categories,
                        handicaps = handicaps,
                        reader = reader,
                        index = i
                )
            } catch (rfe: RegistrationFileException) {
                rfe.printStackTrace()
                continue
            }
        }
        return registrations
    }
}