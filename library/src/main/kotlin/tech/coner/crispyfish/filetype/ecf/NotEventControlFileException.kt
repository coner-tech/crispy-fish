package tech.coner.crispyfish.filetype.ecf

import java.nio.file.Path

class NotEventControlFileException(file: Path) : IllegalArgumentException(
        "File is not an ecf file: $file"
)
