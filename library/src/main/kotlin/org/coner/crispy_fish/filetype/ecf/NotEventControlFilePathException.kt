package org.coner.crispy_fish.filetype.ecf

import java.nio.file.Path

class NotEventControlFilePathException(path: Path) : IllegalArgumentException("Path is not an ecf file: $path")
