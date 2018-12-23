package org.coner.crispyfish.filetype.ecf

import java.io.File

class NotEventControlFileException(file: File) : IllegalArgumentException(
        "File is not an ecf file: $file"
)
