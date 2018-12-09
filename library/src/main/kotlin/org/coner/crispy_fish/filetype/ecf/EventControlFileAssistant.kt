package org.coner.crispy_fish.filetype.ecf

import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.PathMatcher

class EventControlFileAssistant {

    private val eventControlFileMatcher: PathMatcher by lazy {
        FileSystems.getDefault().getPathMatcher("glob:*.ecf")
    }

    fun isEventControlFilePath(path: Path): Boolean {
        return eventControlFileMatcher.matches(path.fileName) && !path.toFile().isDirectory
    }

}
