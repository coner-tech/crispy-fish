package org.coner.crispyfish.filetype.registration

import org.coner.crispyfish.filetype.staging.StagingLineException
import org.coner.crispyfish.filetype.staging.StagingLineRegistration
import org.coner.crispyfish.model.ClassDefinition
import org.coner.crispyfish.model.Registration
import org.coner.crispyfish.model.RegistrationResult
import org.coner.crispyfish.model.RegistrationRun
import java.util.regex.Pattern

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
                memberNumber = reader.readMemberNumber(index)?.let {
                    if (it.isEmpty()) null else it
                },
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
                ),
                runs = reader.readRunTimes(index)
                        .zip(reader.readRunPenalties(index))
                        .map { (time, penalty) -> RegistrationRun(
                                    time = time,
                                    penalty = toPenalty(penalty)
                            )
                        }
        )
    }

    private fun toPenalty(penalty: String): RegistrationRun.Penalty? {
        if (penalty.isEmpty()) {
            return null
        }
        val coneMatcher = PATTERN_PENALTY_CONE.matcher(penalty)
        return when {
            coneMatcher.matches() -> RegistrationRun.Penalty.Cone(
                    count = coneMatcher.group(1).toInt()
            )
            PATTERN_PENALTY_DID_NOT_FINISH.matcher(penalty).matches() -> RegistrationRun.Penalty.DidNotFinish
            PATTERN_PENALTY_DISQUALIFIED.matcher(penalty).matches() -> RegistrationRun.Penalty.Disqualified
            else -> RegistrationRun.Penalty.Unknown
        }
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

    companion object {
        private val PATTERN_PENALTY_CONE = Pattern.compile("^(\\d*)$")
        private val PATTERN_PENALTY_DID_NOT_FINISH = Pattern.compile("^DNF$", Pattern.CASE_INSENSITIVE)
        private val PATTERN_PENALTY_DISQUALIFIED = Pattern.compile("^DSQ$", Pattern.CASE_INSENSITIVE)
    }
}