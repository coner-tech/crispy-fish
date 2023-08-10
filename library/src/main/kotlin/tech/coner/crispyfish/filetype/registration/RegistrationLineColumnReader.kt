package tech.coner.crispyfish.filetype.registration

import java.util.regex.Pattern

internal class RegistrationLineColumnReader(registrationFile: RegistrationFile) {

    private val lines by lazy { registrationFile.file.readLines() }
    private val headings by lazy {
        val headingLine = lines.firstOrNull() ?: throw RegistrationFileException(
                "Registration file first line lacks headings"
        )
        headingLine.split("\t")
    }
    val registrationLines by lazy {
        lines.subList(1, lines.size)
            .asSequence()
            .map { it.split("\t") }
            .toList()
    }

    private val columnIndices = mutableMapOf<RegistrationColumn, Int>()
    val runTimeColumns: List<RegistrationColumn.RunTime> by lazy {
        val timesPattern = Pattern.compile("^Run (\\d*)$")
        headings.map { heading -> timesPattern.matcher(heading) }
            .filter { matcher -> matcher.matches() }
            .map { matcher -> matcher.group(1).toInt() }
            .map { runNumber -> RegistrationColumn.RunTime(runNumber) }
    }
    val runPenaltyColumns: List<RegistrationColumn.RunPenalty> by lazy {
        val penaltiesPattern = Pattern.compile("^Pen (\\d*)$")
        headings.map { heading -> penaltiesPattern.matcher(heading) }
            .filter { matcher -> matcher.matches() }
            .map { matcher -> matcher.group(1).toInt() }
            .map { runNumber -> RegistrationColumn.RunPenalty(runNumber) }
    }
    val customColumns: List<RegistrationColumn.Custom>

    init {
        val foundCustomColumns = mutableListOf<RegistrationColumn.Custom>()
        val result = headings.mapIndexed { index, heading -> when {
            RegistrationColumn.Class.isHeading(heading) -> RegistrationColumn.Class to index
            RegistrationColumn.Number.isHeading(heading) -> RegistrationColumn.Number to index
            RegistrationColumn.FirstName.isHeading(heading) -> RegistrationColumn.FirstName to index
            RegistrationColumn.LastName.isHeading(heading) -> RegistrationColumn.LastName to index
            RegistrationColumn.CarModel.isHeading(heading) -> RegistrationColumn.CarModel to index
            RegistrationColumn.CarColor.isHeading(heading) -> RegistrationColumn.CarColor to index
            RegistrationColumn.GridNumber.isHeading(heading) -> RegistrationColumn.GridNumber to index
            RegistrationColumn.MemberNumber.isHeading(heading) -> RegistrationColumn.MemberNumber to index
            RegistrationColumn.MembershipExpires.isHeading(heading) -> RegistrationColumn.MembershipExpires to index
            RegistrationColumn.DateOfBirth.isHeading(heading) -> RegistrationColumn.DateOfBirth to index
            RegistrationColumn.Age.isHeading(heading) -> RegistrationColumn.Age to index
            RegistrationColumn.Registered.isHeading(heading) -> RegistrationColumn.Registered to index
            RegistrationColumn.RegisteredCheckedIn.isHeading(heading) -> RegistrationColumn.RegisteredCheckedIn to index
            RegistrationColumn.OnlineRegistration.isHeading(heading) -> RegistrationColumn.OnlineRegistration to index
            RegistrationColumn.Paid.isHeading(heading) -> RegistrationColumn.Paid to index
            RegistrationColumn.FeeType.isHeading(heading) -> RegistrationColumn.FeeType to index
            RegistrationColumn.PaymentMethod.isHeading(heading) -> RegistrationColumn.PaymentMethod to index
            RegistrationColumn.PaymentAmount.isHeading(heading) -> RegistrationColumn.PaymentAmount to index
            RegistrationColumn.AnnualTech.isHeading(heading) -> RegistrationColumn.AnnualTech to index
            RegistrationColumn.AnnualWaiver.isHeading(heading) -> RegistrationColumn.AnnualWaiver to index
            RegistrationColumn.Rookie.isHeading(heading) -> RegistrationColumn.Rookie to index
            RegistrationColumn.RunHeat.isHeading(heading) -> RegistrationColumn.RunHeat to index
            RegistrationColumn.WorkHeat.isHeading(heading) -> RegistrationColumn.WorkHeat to index
            RegistrationColumn.WorkAssignment.isHeading(heading) -> RegistrationColumn.WorkAssignment to index
            RegistrationColumn.CheckIn.isHeading(heading) -> RegistrationColumn.CheckIn to index
            RegistrationColumn.Sponsor.isHeading(heading) -> RegistrationColumn.Sponsor to index
            RegistrationColumn.TireBrand.isHeading(heading) -> RegistrationColumn.TireBrand to index
            RegistrationColumn.TireSize.isHeading(heading) -> RegistrationColumn.TireSize to index
            RegistrationColumn.Region.isHeading(heading) -> RegistrationColumn.Region to index
            RegistrationColumn.RawResultTime.isHeading(heading) -> RegistrationColumn.RawResultTime to index
            RegistrationColumn.RawResultPosition.isHeading(heading) -> RegistrationColumn.RawResultPosition to index
            RegistrationColumn.PaxResultTime.isHeading(heading) -> RegistrationColumn.PaxResultTime to index
            RegistrationColumn.PaxResultPosition.isHeading(heading) -> RegistrationColumn.PaxResultPosition to index
            RegistrationColumn.ClassResultTime.isHeading(heading) -> RegistrationColumn.ClassResultTime to index
            RegistrationColumn.ClassResultPosition.isHeading(heading) -> RegistrationColumn.ClassResultPosition to index
            RegistrationColumn.ClassResultDiff.isHeading(heading) -> RegistrationColumn.ClassResultDiff to index
            RegistrationColumn.ClassResultFromFirst.isHeading(heading) -> RegistrationColumn.ClassResultFromFirst to index
            RegistrationColumn.BestRun.isHeading(heading) -> RegistrationColumn.BestRun to index
            runTimeColumns.any { it.isHeading(heading) } -> runTimeColumns.single { it.isHeading(heading) } to index
            runPenaltyColumns.any { it.isHeading(heading) } -> runPenaltyColumns.single { it.isHeading(heading) } to index
            else -> {
                val custom = RegistrationColumn.Custom(heading)
                foundCustomColumns += custom
                custom to index
            }
        } } as List<Pair<RegistrationColumn, Int>>
        columnIndices.putAll(result)
        customColumns = foundCustomColumns
    }

    fun get(row: Int, column: RegistrationColumn): String? {
        val columnIndex: Int = columnIndices.getValue(column)
        return registrationLines[row][columnIndex].ifEmpty { null }
    }
}
