package org.coner.crispyfish.filetype.registration

import org.coner.crispyfish.filetype.staging.StagingLineException
import org.coner.crispyfish.filetype.staging.StagingLineRegistration
import org.coner.crispyfish.model.ClassDefinition
import org.coner.crispyfish.model.Registration
import org.coner.crispyfish.model.RegistrationResult

class RegistrationMapper(
) {

    fun toRegistration(
            categories: List<ClassDefinition>,
            handicaps: List<ClassDefinition>,
            reader: RegistrationLineColumnReader,
            index: Int
    ): Registration {
        val lineClass = reader.readClass(index)?.toUpperCase()
                ?: throw RegistrationFileException("Index $index lacks class")
        var handicapAbbreviation = handicaps.firstOrNull {
            it.abbreviation == lineClass
        }?.abbreviation

        val category = if (handicapAbbreviation == null) {
            val category = categories.firstOrNull {
                lineClass.startsWith(it.abbreviation)
            } ?: throw RegistrationFileException("""
                Failed to resolve category and handicap for registration line.
                index: $index
                classing: $lineClass
                number: ${reader.readNumber(index)}
            """.trimIndent()
            )
            handicapAbbreviation = lineClass.replaceFirst(category.abbreviation, "")
            category
        } else null

        val handicap = handicaps.firstOrNull {
            it.abbreviation == handicapAbbreviation
        } ?: throw RegistrationFileException("""
            No handicap found matching registration line.
            classing: $lineClass
        """.trimIndent())

        return Registration(
                category = category,
                handicap = handicap,
                number = reader.readNumber(index) ?: throw RegistrationFileException(
                        "Index $index lacks number"
                ),
                firstName = reader.readFirstName(index) ?: throw RegistrationFileException(
                        "Index $index lacks firstName"
                ),
                lastName = reader.readLastName(index) ?: throw RegistrationFileException(
                        "Index $index lacks lastName"
                ),
                carModel = reader.readCarModel(index) ?: throw RegistrationFileException(
                        "Index $index lacks car model"
                ),
                carColor = reader.readCarColor(index) ?: throw RegistrationFileException(
                        "Index $index lacks car color"
                ),
                rawResult = RegistrationResult(
                        time = reader.readRawResultTime(index) ?: throw RegistrationFileException(
                                "Index $index lacks raw result time"
                        ),
                        position = reader.readRawResultPosition(index)?.toInt() ?: throw RegistrationFileException(
                                "Index $index lacks raw result position"
                        )
                ),
                paxResult = RegistrationResult(
                        time = reader.readPaxResultTime(index) ?: throw RegistrationFileException(
                                "Index $index lacks pax result time"
                        ),
                        position = reader.readPaxResultPosition(index)?.toInt() ?: throw RegistrationFileException(
                                "Index $index lacks pax result position"
                        )
                ),
                classResult = RegistrationResult(
                        time = reader.readClassResultTime(index) ?: throw RegistrationFileException(
                                "Index $index lacks class result time"
                        ),
                        position = reader.readClassResultPosition(index)?.toInt()
                )
        )
    }

    fun toRegistration(
            stagingLineRegistration: StagingLineRegistration,
            registrations: List<Registration>,
            categories: List<ClassDefinition>,
            handicaps: List<ClassDefinition>
    ): Registration {
        if (stagingLineRegistration.classing == null) throw IllegalArgumentException()
        if (stagingLineRegistration.number == null) throw IllegalArgumentException()

        var handicapAbbreviation = handicaps.firstOrNull {
            it.abbreviation == stagingLineRegistration.classing
        }?.abbreviation

        val category = if (handicapAbbreviation == null) {
            val category = categories.firstOrNull {
                stagingLineRegistration.classing.startsWith(it.abbreviation)
            } ?: throw StagingLineException("""
                Failed to resolve category and handicap for staging line.
                classing: ${stagingLineRegistration.classing}
            """.trimIndent()
            )
            handicapAbbreviation = stagingLineRegistration.classing.replaceFirst(category.abbreviation, "")
            category
        } else null

        val handicap = handicaps.firstOrNull {
            it.abbreviation == handicapAbbreviation
        } ?: throw StagingLineException("""
            No handicap found matching staging line.
            classing: ${stagingLineRegistration.classing}
        """.trimIndent()

        )
        return registrations.firstOrNull {
            it.category == category
            && it.handicap == handicap
            && it.number == stagingLineRegistration.number
        } ?: throw StagingLineException("""
            No registration found matching staging line identity.
            classing: ${stagingLineRegistration.classing}
            number: ${stagingLineRegistration.number}
        """.trimIndent())
    }
}