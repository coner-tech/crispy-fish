package tech.coner.crispyfish.internal.filetype.registration

import java.nio.file.Path

@JvmInline
value class RegistrationFile(
    val file: Path
) {
    internal fun columnReader() = RegistrationLineColumnReader(this)
}