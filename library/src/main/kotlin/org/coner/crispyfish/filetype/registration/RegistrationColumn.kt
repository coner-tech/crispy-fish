package org.coner.crispyfish.filetype.registration

import java.util.regex.Pattern

internal sealed class RegistrationColumn(val heading: Pattern) {

    fun isHeading(candidate: String) = heading.matcher(candidate).matches()

    open class Literal(heading: String) : RegistrationColumn(Pattern.compile(Pattern.quote(heading))) {
        val literalHeading = heading
    }
    object Class : Literal("Class")
    object Number : Literal("Number")
    object FirstName : Literal("First Name")
    object LastName : Literal("Last Name")
    object CarModel : Literal("Car Model")
    object CarColor : Literal("Car Color")
    object GridNumber : Literal("Grid #")
    object Member : Literal("Member")
    object MemberNumber : Literal("Member #")
    object MembershipExpires : Literal("Expires")
    object DateOfBirth : Literal("DOB")
    object Age : Literal("Age")
    object Registered : Literal("Registered")
    object RegisteredCheckedIn : Literal("Reg.CheckIn")
    object OnlineRegistration : Literal("OLR")
    object Paid : Literal("Paid")
    object FeeType : Literal("Fee Type")
    object PaymentMethod : Literal("Method")
    object PaymentAmount : Literal("Amnt.")
    object AnnualTech : Literal("Annual Tech")
    object AnnualWaiver : Literal("Annual Waiver")
    object Rookie : Literal("Rookie")
    object RunHeat : Literal("Run Heat")
    object WorkHeat : Literal("Work Heat")
    object WorkAssignment : Literal("Work Assignment")
    object CheckIn : Literal("Checkin")
    object Sponsor : Literal("Sponsor")
    object TireBrand : Literal("Tire Brand")
    object TireSize : Literal("Tire Size")
    object Region : Literal("Region")
    object RawResultTime : Literal("Raw Time")
    object RawResultPosition : Literal("Raw Pos.")
    object PaxResultTime : Literal("Pax Time")
    object PaxResultPosition : Literal("Pax Pos.")
    object ClassResultTime : Literal("Total")
    object ClassResultPosition : Literal("Prev. Pos.")
    object ClassResultDiff : Literal("Diff.")
    object ClassResultFromFirst : Literal("From 1st")
    object BestRun : Literal("Best Run")
    class RunTime(runNumber: Int) : Literal("Run $runNumber")
    class RunPenalty(runNumber: Int) : Literal("Pen $runNumber")
    class Custom(name: String) : Literal(name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegistrationColumn

        if (heading != other.heading) return false

        return true
    }

    override fun hashCode(): Int {
        return heading.hashCode()
    }
}