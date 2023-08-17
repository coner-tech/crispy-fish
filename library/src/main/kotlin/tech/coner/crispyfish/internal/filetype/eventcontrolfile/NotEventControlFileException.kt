package tech.coner.crispyfish.internal.filetype.eventcontrolfile

import java.nio.file.Path

class NotEventControlFileException(file: Path) : IllegalArgumentException(
        "File is not an ecf file: $file"
)
