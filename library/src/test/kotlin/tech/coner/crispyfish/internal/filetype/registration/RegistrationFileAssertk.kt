package tech.coner.crispyfish.internal.filetype.registration

import assertk.Assert
import assertk.assertions.prop

internal fun Assert<RegistrationFile>.file() = prop(RegistrationFile::file)