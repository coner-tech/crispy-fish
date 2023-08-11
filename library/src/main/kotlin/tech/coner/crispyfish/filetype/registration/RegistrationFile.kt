package tech.coner.crispyfish.filetype.registration

import java.nio.file.Path

class RegistrationFile(
        val file: Path
) {
    internal fun columnReader() = RegistrationLineColumnReader(this)
}