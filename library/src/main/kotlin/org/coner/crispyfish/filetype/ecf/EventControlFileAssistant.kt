package org.coner.crispyfish.filetype.ecf

import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.PathMatcher

class EventControlFileAssistant {

    fun isEventControlFile(file: File): Boolean {
        return file.extension == "ecf" && !file.isDirectory
    }

}
