package org.coner.crispyfish.filetype.registration

sealed class RegistrationColumns(val heading: String, var index: Int? = null) {
    class Class : RegistrationColumns("Class")
    class Number : RegistrationColumns("Number")
    class FirstName : RegistrationColumns("First Name")
    class LastName : RegistrationColumns("Last Name")
    class CarModel : RegistrationColumns("Car Model")
    class CarColor : RegistrationColumns("Car Color")
    class MemberNumber : RegistrationColumns("Member #")
    class RawResultTime : RegistrationColumns("Raw Time")
    class RawResultPosition : RegistrationColumns("Raw Pos.")
    class PaxResultTime : RegistrationColumns("Pax Time")
    class PaxResultPosition : RegistrationColumns("Pax Pos.")
    class ClassResultTime : RegistrationColumns("Total")
    class ClassResultPosition : RegistrationColumns("Prev. Pos.")
    class BestRun : RegistrationColumns("Best Run")
    class RunTime(runNumber: Int) : RegistrationColumns("Run $runNumber")
    class RunPenalty(runNumber: Int) : RegistrationColumns("Pen $runNumber")
}