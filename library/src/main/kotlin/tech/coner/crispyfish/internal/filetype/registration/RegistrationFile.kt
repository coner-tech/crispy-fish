package tech.coner.crispyfish.internal.filetype.registration

import java.nio.file.Path

@JvmInline
internal value class RegistrationFile(
    val file: Path
) {
    internal fun columnReader() = RegistrationLineColumnReader(this)
}