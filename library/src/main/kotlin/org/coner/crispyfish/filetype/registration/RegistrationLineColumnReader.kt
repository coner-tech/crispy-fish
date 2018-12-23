package org.coner.crispyfish.filetype.registration

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
}
