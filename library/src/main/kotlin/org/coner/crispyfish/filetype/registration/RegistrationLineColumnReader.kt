package org.coner.crispyfish.filetype.registration

import org.coner.crispyfish.model.RegistrationRun
import java.util.regex.Pattern
import kotlin.streams.toList

class RegistrationLineColumnReader(registrationFile: RegistrationFile) {

    private val lines by lazy { registrationFile.file.readLines() }
    private val headings by lazy {
        val headingLine = lines.firstOrNull() ?: throw RegistrationFileException(
                "Registration file first line lacks headings"
        )
        headingLine.split("\t")
    }
    val registrationLines by lazy {
        lines.subList(1, lines.size)
                .parallelStream()
                .map { it.split("\t") }
                .toList()
    }

    private fun <C : RegistrationColumns> column(column: C): C {
        return column.apply {
            val index = headings.indexOf(heading)
            if (index < 0) throw ArrayIndexOutOfBoundsException()
            this.index = index
        }
    }

    private val classColumn by lazy { column(RegistrationColumns.Class()) }
    private val numberColumn by lazy { column(RegistrationColumns.Number()) }
    private val firstNameColumn by lazy { column(RegistrationColumns.FirstName()) }
    private val lastNameColumn by lazy { column(RegistrationColumns.LastName()) }
    private val carModelColumn by lazy { column(RegistrationColumns.CarModel()) }
    private val carColorColumn by lazy { column(RegistrationColumns.CarColor()) }
    private val memberNumber by lazy { column(RegistrationColumns.MemberNumber()) }
    private val rawResultTime by lazy { column(RegistrationColumns.RawResultTime()) }
    private val rawResultPosition by lazy { column(RegistrationColumns.RawResultPosition()) }
    private val paxResultTime by lazy { column(RegistrationColumns.PaxResultTime()) }
    private val paxResultPosition by lazy { column(RegistrationColumns.PaxResultPosition()) }
    private val classResultTime by lazy { column(RegistrationColumns.ClassResultTime()) }
    private val classResultPosition by lazy { column(RegistrationColumns.ClassResultPosition()) }
    private val bestRun by lazy { column(RegistrationColumns.BestRun()) }
    private val runTimes by lazy {
        val timesPattern = Pattern.compile("^Run (\\d*)$")
        headings.map { heading -> timesPattern.matcher(heading) }
                .filter { matcher -> matcher.matches() }
                .map { matcher -> matcher.group(1).toInt() }
                .map { runNumber -> column(RegistrationColumns.RunTime(runNumber)) }

    }
    private val runPenalties by lazy {
        val penaltiesPattern = Pattern.compile("^Pen (\\d*)$")
        headings.map { heading -> penaltiesPattern.matcher(heading) }
                .filter { matcher -> matcher.matches() }
                .map { matcher -> matcher.group(1).toInt() }
                .map { runNumber -> column(RegistrationColumns.RunPenalty(runNumber)) }
    }

    fun readClass(index: Int): String? {
        return registrationLines[index][classColumn.index!!]
    }

    fun readNumber(index: Int): String? {
        return registrationLines[index][numberColumn.index!!]
    }

    fun readFirstName(index: Int): String? {
        return registrationLines[index][firstNameColumn.index!!]
    }

    fun readLastName(index: Int): String? {
        return registrationLines[index][lastNameColumn.index!!]
    }

    fun readCarModel(index: Int): String? {
        return registrationLines[index][carModelColumn.index!!]
    }

    fun readCarColor(index: Int): String? {
        return registrationLines[index][carColorColumn.index!!]
    }

    fun readMemberNumber(index: Int): String? {
        return registrationLines[index][memberNumber.index!!]
    }

    fun readRawResultTime(index: Int): String? {
        return registrationLines[index][rawResultTime.index!!]
    }

    fun readRawResultPosition(index: Int): String? {
        return registrationLines[index][rawResultPosition.index!!]
    }

    fun readPaxResultTime(index: Int): String? {
        return registrationLines[index][paxResultTime.index!!]
    }

    fun readPaxResultPosition(index: Int): String? {
        return registrationLines[index][paxResultPosition.index!!]
    }

    fun readClassResultTime(index: Int): String? {
        return registrationLines[index][classResultTime.index!!]
    }

    fun readClassResultPosition(index: Int): String? {
        return registrationLines[index][classResultPosition.index!!].let {
            if (!it.isEmpty()) {
                it
            } else {
                null
            }
        }
    }

    fun readBestRun(index: Int): String? {
        return registrationLines[index][bestRun.index!!]
    }

    fun readRunTimes(index: Int): List<String> {
        return runTimes.map {
            registrationLines[index][it.index!!]
        }
    }

    fun readRunPenalties(index: Int): List<String> {
        return runPenalties.map {
            registrationLines[index][it.index!!]
        }
    }

}
