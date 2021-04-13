package tech.coner.crispyfish.filetype.registration

import java.io.File

class RegistrationFile(
        val file: File
) {
    internal fun columnReader() = RegistrationLineColumnReader(this)

    internal fun mapper() = RegistrationMapper()

}