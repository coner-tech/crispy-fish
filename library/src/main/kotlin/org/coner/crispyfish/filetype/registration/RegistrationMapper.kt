package org.coner.crispyfish.filetype.registration

import org.coner.crispyfish.model.Numbers
import org.coner.crispyfish.model.Registration

object RegistrationMapper {

    fun toRegistration(
            columnIndices: Map<RegistrationColumns, Int>,
            line: String
    ): Registration {
        val columns = line.split("\t")
        return Registration(
                numbers = Numbers(
                        classing = columns[columnIndices[RegistrationColumns.CLASS]!!],
                        number = columns[columnIndices[RegistrationColumns.NUMBER]!!]
                ),
                firstName = columns[columnIndices[RegistrationColumns.FIRST_NAME]!!],
                lastName = columns[columnIndices[RegistrationColumns.LAST_NAME]!!],
                carModel = columns[columnIndices[RegistrationColumns.CAR_MODEL]!!],
                carColor = columns[columnIndices[RegistrationColumns.CAR_COLOR]!!]
        )
    }
}