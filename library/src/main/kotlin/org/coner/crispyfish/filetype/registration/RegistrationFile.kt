package org.coner.crispyfish.filetype.registration

import org.coner.crispyfish.model.Registration
import java.io.File
import kotlin.streams.toList

class RegistrationFile(
        val file: File,
        private val registrations: MutableList<Registration> = mutableListOf(),
        private val columnIndices: MutableMap<RegistrationColumns, Int> = mutableMapOf()
) {
    fun read() {
        columnIndices.clear()
        registrations.clear()

        val lines = file.readLines()
        try {
            columnIndices.putAll(buildColumnIndices(lines))
            registrations.addAll(buildRegistrations(lines))
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            registrations.clear()
            columnIndices.clear()
        }
    }

    private fun buildColumnIndices(lines: List<String>): Map<RegistrationColumns, Int> {
        val headingLine = lines.firstOrNull() ?: throw RegistrationFileException(
                "Registration file first line lacks headings"
        )
        val columnIndices = mutableMapOf<RegistrationColumns, Int>()
        val headings = headingLine.split("\t")
        RegistrationColumns.values().forEach {
            columnIndices[it] = headings.indexOf(it.heading)
        }
        return columnIndices
    }

    private fun buildRegistrations(lines: List<String>): List<Registration> {
        return lines.subList(1, lines.lastIndex)
                .parallelStream()
                .map { RegistrationMapper.toRegistration(columnIndices, it) }
                .toList()
    }
}