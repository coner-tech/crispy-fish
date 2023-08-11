package tech.coner.crispyfish.filetype.registration

import assertk.Assert
import assertk.assertions.prop

fun Assert<RegistrationFile>.file() = prop(RegistrationFile::file)