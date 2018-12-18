package org.coner.crispyfish.filetype.registration

import java.io.File

class RegistrationFile(
        val file: File
) {
    fun columnReader() = RegistrationLineColumnReader(this)

}