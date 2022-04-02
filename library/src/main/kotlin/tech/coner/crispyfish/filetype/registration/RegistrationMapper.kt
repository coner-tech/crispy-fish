package tech.coner.crispyfish.filetype.registration

import tech.coner.crispyfish.filetype.registration.RegistrationColumn.*
import tech.coner.crispyfish.model.ClassDefinition
import tech.coner.crispyfish.model.Registration
import tech.coner.crispyfish.model.RegistrationResult
import tech.coner.crispyfish.model.RegistrationRun
import java.util.*
import java.util.regex.Pattern

internal class RegistrationMapper(
) {

    fun toRegistration(
        categories: List<ClassDefinition>,
        handicaps: List<ClassDefinition>,
        reader: RegistrationLineColumnReader,
        index: Int
    ): Registration {
        val lineClass = reader.get(index, Class)?.uppercase(Locale.getDefault())

        var handicapAbbreviation = handicaps.firstOrNull { it.abbreviation == lineClass }?.abbreviation
        val category = if (lineClass != null && handicapAbbreviation == null) {
            categories
                .firstOrNull { lineClass.startsWith(it.abbreviation) }
                ?.also { handicapAbbreviation = lineClass.replaceFirst(it.abbreviation, "") }
        } else {
            null
        }

        val handicap = handicaps.firstOrNull { it.abbreviation == handicapAbbreviation }

        return Registration(
            category = category,
            handicap = handicap,
            number = reader.get(index, Number),
            firstName = reader.get(index, FirstName),
            lastName = reader.get(index, LastName),
            carModel = reader.get(index, CarModel),
            carColor = reader.get(index, CarColor),
            sponsor = reader.get(index, Sponsor),
            tireBrand = reader.get(index, TireBrand),
            tireSize = reader.get(index, TireSize),
            region = reader.get(index, Region),
            gridNumber = reader.get(index, GridNumber),
            memberNumber = reader.get(index, MemberNumber),
            membershipExpires = reader.get(index, MembershipExpires),
            dateOfBirth = reader.get(index, DateOfBirth),
            age = reader.get(index, Age),
            registered = reader.get(index, Registered).toBoolean(),
            registeredCheckedIn = reader.get(index, RegisteredCheckedIn).toBoolean(),
            checkIn = reader.get(index, CheckIn).toBoolean(),
            onlineRegistration = reader.get(index, OnlineRegistration).toBoolean(),
            paid = reader.get(index, Paid).toBoolean(),
            feeType = reader.get(index, FeeType),
            paymentMethod = reader.get(index, PaymentMethod),
            paymentAmount = reader.get(index, PaymentAmount),
            annualTech = reader.get(index, AnnualTech).toBoolean(),
            annualWaiver = reader.get(index, AnnualWaiver).toBoolean(),
            rookie = reader.get(index, Rookie).toBoolean(),
            runHeat = reader.get(index, RunHeat)?.toIntOrNull(),
            workHeat = reader.get(index, WorkHeat)?.toIntOrNull(),
            workAssignment = reader.get(index, WorkAssignment),
            rawResult = reader.get(index, RawResultTime)?.let { rawTime ->
                reader.get(index, RawResultPosition)?.toInt()?.let { rawPosition ->
                    RegistrationResult(
                        time = rawTime,
                        position = rawPosition
                    )
                }
            },
            paxResult = reader.get(index, PaxResultTime)?.let { paxTime ->
                reader.get(index, PaxResultPosition)?.toInt()?.let { paxPosition ->
                    RegistrationResult(
                        time =  paxTime,
                        position = paxPosition
                    )
                }
            },
            classResult = reader.get(index, ClassResultTime)?.let { classTime ->
                RegistrationResult(
                    time = classTime,
                    position = reader.get(index, ClassResultPosition)?.toInt() ?: Int.MAX_VALUE
                )
            },
            classResultDiff = reader.get(index, ClassResultDiff),
            classResultFromFirst = reader.get(index, ClassResultFromFirst),
            bestRun = try {
                reader.get(index, BestRun)?.toInt()
            } catch (nfe: NumberFormatException) {
                null
            },
            runs = reader.runTimeColumns
                .map { reader.get(index, it) }
                .zip(reader.runPenaltyColumns.map { reader.get(index, it) })
                .map { (time, penalty) -> RegistrationRun(
                    time = time,
                    penalty = toPenalty(penalty)
                ) },
            custom = reader.customColumns
                .map { it.literalHeading to reader.get(index, it) }
                .toMap()
        )
    }

    private fun toPenalty(penalty: String?): RegistrationRun.Penalty? {
        if (penalty.isNullOrEmpty()) {
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

    private fun String?.toBoolean(): Boolean {
        return this?.startsWith('Y') == true
    }

    companion object {
        private val PATTERN_PENALTY_CONE = Pattern.compile("^(\\d*)$")
        private val PATTERN_PENALTY_DID_NOT_FINISH = Pattern.compile("^DNF$", Pattern.CASE_INSENSITIVE)
        private val PATTERN_PENALTY_DISQUALIFIED = Pattern.compile("^DSQ$", Pattern.CASE_INSENSITIVE)
    }
}